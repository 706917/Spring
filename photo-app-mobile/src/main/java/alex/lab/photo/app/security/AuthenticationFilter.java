package alex.lab.photo.app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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

import alex.lab.photo.app.SpringApplicationContext;
import alex.lab.photo.app.service.UserService;
import alex.lab.photo.app.shared.dto.UserDto;
import alex.lab.photo.app.ui.model.request.UserLogInRequestModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	// Performs actual authentication. 
	public Authentication attemptAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			// Construct an object out of request json payload and assign it to the variable type UserLogInRequestModel
			// to be able to extract parameters values from it
			UserLogInRequestModel credentials = new ObjectMapper()
					.readValue(request.getInputStream(), UserLogInRequestModel.class);
			
			// Construct authentication token out from parameters of the object created and try to authenticate via manager (compare with the records we have in DB)
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getEmail(), 
															credentials.getPassword(), 
															new ArrayList<>())
					);
					
		}
		
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	// If the previous method succeeds, this method is called
	// Produces a JWT for response header

	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) 
			throws IOException, ServletException {
		
		String userName = ((User) authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
							.setSubject(userName)
							.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
							.signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
							.compact(); //Actually builds the JWT and serializes it to a compact, URL-safe string according to the JWT Compact Serialization rules.
		
		
		
		// Inject instance of UserServiceIml class via Spring Context
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceIml");
		// Get the UserDto object via userService, extracting data from db
		UserDto userDto = userService.getUser(userName);
		
		// Assign JWT into response header
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		// Assign UserId into response header
		response.addHeader("UserId", userDto.getUserId());
		
	}	  

}
