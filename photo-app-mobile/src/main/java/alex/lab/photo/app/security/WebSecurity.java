package alex.lab.photo.app.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import alex.lab.photo.app.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests() // Allows restricting access based upon the HttpServletRequest using 
		.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll() // Allows any POST requests to "/users" path for all users
		.anyRequest().authenticated() // Any other requests should be authenticated
		.and().addFilter(getAuthenticationFilter())//adds an instance of our AuthenticationFIlter class as a custom filter with the method which provides special url
		.addFilter(new AuthorizationFilter(authenticationManager())) // authorize authenticated user to act
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // make the connection stateless to prevent authorization headers from being cashed
	}
	
	
	
	
	// Specifies the URL to login 
	public AuthenticationFilter getAuthenticationFilter() throws Exception{
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}

}
