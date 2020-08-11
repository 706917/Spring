package lab.alex.todo.utility;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	private final String CHARACTERS = "0123456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
	private final Random RANDOM = new SecureRandom();
	
	
	public String generatePublicId() {
		int numberOfCharacters = 15;
		return genarateRandomString(numberOfCharacters);
	}
	
	
	
	
	
	private String genarateRandomString(int limit) {
		StringBuilder returnValue = new StringBuilder(limit);		
		
		for (int i = 0; i<limit; i++) {
			returnValue.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
		}
		return returnValue.toString();
	}

}
