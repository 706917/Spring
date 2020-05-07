package alex.lab.photo.app.ui.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import alex.lab.photo.app.service.UserService;
import alex.lab.photo.app.shared.dto.AddressDto;
import alex.lab.photo.app.shared.dto.UserDto;
import alex.lab.photo.app.ui.model.responce.UserRest;

class UserControllerTest {
	
	//********************
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	UserDto userDto;
	final String USER_ID = "fakeUserId";
	
	//*************************

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		userDto = new UserDto();
		
		userDto.setFirstName("Alex");
		userDto.setLastName("Rom");
		userDto.setEmail("test@test.com");
		userDto.setEmeailVerificationStatus(Boolean.FALSE);
		userDto.setEmailVerificationToken(null);
		userDto.setUserId(USER_ID);
		userDto.setAddresses(getAddressesDto());
		userDto.setEncryptedPassword("fakePasword");
		
		
		
	}

	@Test
	void testGetUsers() {

		when(userService.getUserByUserId(anyString())).thenReturn(userDto);
		
		
		UserRest userRest = userController.getUser(USER_ID);
		
		assertNotNull(userRest);
		assertEquals(USER_ID, userRest.getUserId());
		assertEquals(userDto.getFirstName(), userRest.getFirstName());
		assertEquals(userDto.getLastName(), userRest.getLastName());
		assertTrue(userDto.getAddresses().size() == userRest.getAddresses().size());
		
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

	

}
