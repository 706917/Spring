package alex.lab.photo.app.ui.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alex.lab.photo.app.shared.dto.UserDto;
import alex.lab.photo.app.ui.model.request.UserDetailsRequestBody;
import alex.lab.photo.app.ui.model.responce.UserRest;
import alex.lab.photo.app.ui.service.UserServise;

@RestController
@RequestMapping("/users") // http://localhost:8080/users

public class UserController {
	
	@Autowired
	UserServise userService;
	
	@GetMapping
	public String getUser() {
		return "get user was called";
	}
	
	@PostMapping
	public UserRest createUser(
			@RequestBody UserDetailsRequestBody userDetails
			) {
		
		UserRest returnValue = new UserRest(); // A class-instance to populate with data to return with Responce
		
		UserDto userDto = new UserDto(); // A class-instance to use data transfer object
		
		BeanUtils.copyProperties(userDetails, userDto); // A method to copy properties from one object to another
		
		UserDto createdUser = userService.createUser(userDto); // Create Dto-user object to transfer its properties to the returnValue o
		
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping
	public String updateUser() {
		return "update was called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user was called";
	}
	

}
