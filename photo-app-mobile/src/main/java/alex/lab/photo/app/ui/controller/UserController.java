package alex.lab.photo.app.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import alex.lab.photo.app.exceptions.UserServiceException;
import alex.lab.photo.app.service.UserService;
import alex.lab.photo.app.shared.dto.UserDto;
import alex.lab.photo.app.ui.model.request.UserDetailsRequestBody;
import alex.lab.photo.app.ui.model.responce.ErrorMessages;
import alex.lab.photo.app.ui.model.responce.OperationStatusModel;
import alex.lab.photo.app.ui.model.responce.RequestOperationName;
import alex.lab.photo.app.ui.model.responce.RequestOperationStatus;
import alex.lab.photo.app.ui.model.responce.UserRest;

@RestController
@RequestMapping("/users") // http://localhost:8080/users

public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest>getUsers(@RequestParam(value="page", defaultValue="0") int page,
								  @RequestParam(value="limit", defaultValue = "20") int limit) {
		
		List<UserRest> returnValue = new ArrayList<UserRest>();
		
		List<UserDto> users = userService.getUsers(page, limit);
		
		for(UserDto user: users) {
			UserRest userModel = new UserRest();
			BeanUtils.copyProperties(user, userModel);
			returnValue.add(userModel);
		}
		return returnValue;
		
	}
	
	@GetMapping(path="/{id}", 
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) // allow to produce information in either XML and JSON formats
	public UserRest getUser(@PathVariable String id) {
		
		UserRest returnValue = new UserRest();
		
		UserDto userDto = userService.getUserByUserId(id);
		
		BeanUtils.copyProperties(userDto, returnValue);
		
		return returnValue;
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
				 consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) // allow to consume information in either XML and JSON formats
	public UserRest createUser(@RequestBody UserDetailsRequestBody userDetails) throws Exception {
		
		if (userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserRest returnValue = new UserRest(); // A class-instance to populate with data to return with Responce
		
		UserDto userDto = new UserDto(); // A class-instance to use data transfer object
		
		BeanUtils.copyProperties(userDetails, userDto); // A method to copy properties from one object to another
		
		UserDto createdUser = userService.createUser(userDto); // Create Dto-user object to transfer its properties to the returnValue o
		
		BeanUtils.copyProperties(createdUser, returnValue);
		
		return returnValue;
	}
	
	@PutMapping(path="/{id}",
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
				consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) 	
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestBody userDetails) {

		UserRest returnValue = new UserRest(); 
		
		UserDto userDto = new UserDto(); 
		
		BeanUtils.copyProperties(userDetails, userDto); 
		
		UserDto updatedUser = userService.updateUser(id, userDto); 
		
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}",
				   produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		userService.deleteUser(id);
		
		return returnValue;
	}
	

}
