package alex.lab.photo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import alex.lab.photo.app.security.AppProperties;

@SpringBootApplication
public class PhotoAppMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppMobileApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}
	
//	@Bean(name="appProperties")
//	public AppProperties getAppProperties() {
//		return new AppProperties();
//	}

}
