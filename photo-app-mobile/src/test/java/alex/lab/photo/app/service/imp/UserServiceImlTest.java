package alex.lab.photo.app.service.imp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import alex.lab.photo.app.io.entity.UserEntity;
import alex.lab.photo.app.io.repositories.UserRepository;
import alex.lab.photo.app.shared.dto.UserDto;

class UserServiceImlTest {
	
	@InjectMocks
	UserServiceIml userService;
	
	@Mock
	UserRepository userRepository; // mock it as soon as it declared out of method tested  over here

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testGetUser() {

		// Stab object
		UserEntity user = new UserEntity();
		
		user.setId(1L);
		user.setEmail("test@test.com");
		user.setFirstName("Alex");
		user.setLastName("Roman");
		user.setEncryptedPassword("vaergfw5tyw45h");
		user.setUserId("oiueirbn");
		
		//Fake the method behavior		
		when( userRepository.findByEmail( anyString() )).thenReturn(user);
		
		//test instance
		UserDto userDto = userService.getUser("anyString@vcerv.com");
		
		
		// Series of assertions to check
		assertNotNull(userDto);
		assertEquals("Alex", userDto.getFirstName());
		
	}
	
	@Test
	final void testGetUser_UsernameNotFouundException() {
		
		when( userRepository.findByEmail( anyString() )).thenReturn(null);
		
		assertThrows(UsernameNotFoundException.class, 
				
				()->{
					userService.getUser("anyString@vcerv.com");
					}
				);
		
	}

}
