package lab.alex.todo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lab.alex.todo.ui.model.request.UserLogInRequestModel;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	
	
	// ************* Attributes ********************	
	private final AuthenticationManager authenticationManager;
	
	
	

	// ************ Constructor with fields ****************
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}




	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			UserLogInRequestModel creds = new ObjectMapper()
					.readValue(request.getInputStream(), UserLogInRequestModel.class);
			
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(),
							creds.getPassword(),
							new ArrayList<>()));
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}




	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String userName = ((User) authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
				.compact();


		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);;
	}
	
	
	


	
	
	
	
	
	

}
