package alex.lab.photo.app.service.imp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import alex.lab.photo.app.io.entity.AddressEntity;
import alex.lab.photo.app.io.entity.UserEntity;
import alex.lab.photo.app.io.repositories.UserRepository;
import alex.lab.photo.app.shared.Utils;
import alex.lab.photo.app.shared.dto.AddressDto;
import alex.lab.photo.app.shared.dto.UserDto;

class UserServiceImlTest {
	
	@InjectMocks
	UserServiceIml userService;
	
	@Mock
	UserRepository userRepository; // mock it as soon as it declared out of method tested  over here
	
	@Mock
	Utils utils;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// Since we have multi-usage of followed variables - create them as local variables
	String fakeUserId = "fakeUserId";
	String fakePasword = "fakePassword";
	
	// Stab local object
	UserEntity userEntity;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		// initialize local object
		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setEmail("test@test.com");
		userEntity.setFirstName("Alex");
		userEntity.setLastName("Roman");
		userEntity.setEncryptedPassword(fakePasword);
		userEntity.setUserId(fakeUserId);
		userEntity.setAddresses(getAddressesEntity());
	}

	@Test
	final void testGetUser() {
		
		//Fake the method behavior		
		when( userRepository.findByEmail( anyString() )).thenReturn(userEntity);
		
		//test instance
		UserDto userDto = userService.getUser("anyString@vcerv.com");
		
		
		// Series of assertions to check
		assertNotNull(userDto);
		assertEquals("Alex", userDto.getFirstName());
		
	}
	
	@Test
	final void testGetUser_UsernameNotFoundException() {
		
		when( userRepository.findByEmail( anyString() )).thenReturn(null);
		
		assertThrows(UsernameNotFoundException.class, 
				
				()->{
					userService.getUser("anyString@vcerv.com");
					}
				);
		
	}
	
	
	@Test
	final void testCreateUser() {
		
		
		when(userRepository.findByEmail( anyString() )).thenReturn(null);
		when(utils.generateUserId(anyInt())).thenReturn(fakeUserId);
		when(utils.generateAddressId(anyInt())).thenReturn("anyururu");
		when(bCryptPasswordEncoder.encode(anyString())).thenReturn(fakePasword);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
		
		
		// Stab userDto object preparation	
		
		UserDto userDto = new UserDto();
		userDto.setAddresses(getAddressesDto());
		userDto.setFirstName("Alex");
		userDto.setLastName("Rom");
		userDto.setPassword(fakePasword);
		userDto.setEmail("test@test.com");
		
		
		// Test instance
		UserDto storedUserDetails = userService.createUser(userDto);
		
		assertNotNull(storedUserDetails);
		assertNotNull(storedUserDetails.getEncryptedPassword());
		assertNotNull(storedUserDetails.getUserId());
		
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
		assertEquals(userEntity.getEmail(), storedUserDetails.getEmail());
		assertEquals(userEntity.getAddresses().size(), storedUserDetails.getAddresses().size());
		
		// How many times these methods actually called
		int generatedAddresses = storedUserDetails.getAddresses().size();
		verify(utils,times(generatedAddresses)).generateAddressId(15);
		verify(bCryptPasswordEncoder, times(1)).encode(fakePasword);
		verify(userRepository, times(1)).save(any(UserEntity.class));
		

	}
	
	
	
	
	private List<AddressDto> getAddressesDto(){
		AddressDto shippingAddressDto = new AddressDto();
		shippingAddressDto.setType("shipping");
		shippingAddressDto.setCity("Niantic");
		shippingAddressDto.setCountry("USA");
		shippingAddressDto.setPostalCode("684655");
		shippingAddressDto.setStreetName("85 Main str.");
		
		AddressDto billingAddressDto = new AddressDto();
		billingAddressDto.setType("billing");
		billingAddressDto.setCity("Niantic");
		billingAddressDto.setCountry("USA");
		billingAddressDto.setPostalCode("684655");
		billingAddressDto.setStreetName("85 Main str.");
		
		
		List<AddressDto> addresses = new ArrayList<>();
		addresses.add(shippingAddressDto);
		addresses.add(billingAddressDto);
		
		return addresses;		
	}
	
	private List<AddressEntity> getAddressesEntity(){
		
		List<AddressDto> addresses = getAddressesDto();
		Type listType = new TypeToken<List<AddressEntity>>() {}.getType();
		return new ModelMapper().map(addresses, listType);
		
	}

}
