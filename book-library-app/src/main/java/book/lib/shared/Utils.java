package book.lib.shared;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	
	public String generateBookId() {
		
		int length = 8;
		String alphabet = "1234567890QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
		StringBuilder id = new StringBuilder();
		int count=0;
		
		while(count<=length) {
			id.append(alphabet.charAt((int)(Math.random()*alphabet.length())));
			count++;
		}
		return id.toString();
		
	}

}
