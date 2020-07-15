package lab.alex.todo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoMultiUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoMultiUserApplication.class, args);
	}
	
	@Bean
	public ModelMapper mapper() {
		ModelMapper mapper = null;
		if (mapper == null) mapper = new ModelMapper();
		return mapper;
	}

}
