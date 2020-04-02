package alex.lab.photo.app.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import alex.lab.photo.app.ui.model.request.UserLogInRequestModel;

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
					new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword(), new ArrayList())
					);
					
		}
		
		catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) 
			throws IOException, ServletException {
		
		
		

	}
	
	
	
	
	
	  

}
