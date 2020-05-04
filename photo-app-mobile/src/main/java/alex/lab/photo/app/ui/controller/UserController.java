package alex.lab.photo.app.ui.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import alex.lab.photo.app.exceptions.UserServiceException;
import alex.lab.photo.app.service.AddressService;
import alex.lab.photo.app.service.UserService;
import alex.lab.photo.app.shared.dto.AddressDto;
import alex.lab.photo.app.shared.dto.UserDto;
import alex.lab.photo.app.ui.model.request.UserDetailsRequestBody;
import alex.lab.photo.app.ui.model.responce.AddressRest;
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
	
	@Autowired
	AddressService addressService;
	
	
	
	
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest>getUsers(@RequestParam(value="page", defaultValue="0") int page,
								  @RequestParam(value="limit", defaultValue = "20") int limit) {
		
		List<UserRest> returnValue = new ArrayList<UserRest>();
		
		List<UserDto> users = userService.getUsers(page, limit);
		
		for(UserDto user: users) {
			UserRest userModel = new ModelMapper().map(user, UserRest.class);//new UserRest();
			//BeanUtils.copyProperties(user, userModel);
			returnValue.add(userModel);
		}
		return returnValue;
		
	}
	
	@GetMapping(path="/{id}", 
				produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) // allow to produce information in either XML and JSON formats
	public UserRest getUser(@PathVariable String id) {
		
		UserRest returnValue = new UserRest();
		
		UserDto userDto = userService.getUserByUserId(id);
		
		//BeanUtils.copyProperties(userDto, returnValue);
		returnValue = new ModelMapper().map(userDto, UserRest.class);
		
		return returnValue;
	}
	
	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
				 consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}) // allow to consume information in either XML and JSON formats
	public UserRest createUser(@RequestBody UserDetailsRequestBody userDetails) throws Exception {
		
		if (userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		
		UserRest returnValue = new UserRest(); // A class-instance to populate with data to return with Response
		
//		UserDto userDto = new UserDto(); // A class-instance to use data transfer object		
//		BeanUtils.copyProperties(userDetails, userDto); // A method to copy properties from one object to another
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto); // Create Dto-user object to transfer its properties to the returnValue o		
		//BeanUtils.copyProperties(createdUser, returnValue);
		returnValue = modelMapper.map(createdUser, UserRest.class);
		
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
	
	@GetMapping(path="/{userId}/addresses",
				produces = {MediaType.APPLICATION_JSON_VALUE, 
							MediaType.APPLICATION_XML_VALUE,
							"application/hal+json",
							"application/hal+xml"})
	public CollectionModel<AddressRest> getUserAddresses(@PathVariable String userId){
		
		List <AddressRest> returnValue = new ArrayList<>();
		
		List<AddressDto>  addressesDto = addressService.getAddresses(userId);
		
		if(addressesDto!=null && !addressesDto.isEmpty()) {
			
			Type listType = new TypeToken<List<AddressRest>>() {}.getType(); // to infer the datatype at the runtime
			returnValue = new ModelMapper().map(addressesDto, listType);
			
			for(AddressRest address : returnValue) {
				
				Link addressLink = linkTo(methodOn(UserController.class).getUserAddress(userId, address.getAddressId())).withSelfRel();
				address.add(addressLink);
				
				Link userLink = linkTo(methodOn(UserController.class).getUser(userId)).withRel("user");				
				address.add(userLink);
				
			}
		}
		
		return new CollectionModel<>(returnValue);		
	}
	
	@GetMapping(path="/{userId}/addresses/{addressId}",
				produces = {MediaType.APPLICATION_JSON_VALUE, 
							MediaType.APPLICATION_XML_VALUE,
							"application/hal+json",
							"application/hal+xml"})
	public EntityModel<AddressRest> getUserAddress(@PathVariable String addressId, @PathVariable String userId) {
		
		AddressDto addressDto = addressService.getAddress(addressId);
		//Link addressLink = linkTo(UserController.class).slash(userId).slash("addresses").slash(addressId).withSelfRel();
		Link addressLink = linkTo(methodOn(UserController.class).getUserAddress(addressId, userId)).withSelfRel();
		
		//Link addressesLink = linkTo(UserController.class).slash(userId).slash("addresses").withRel("addresses");
		Link addressesLink = linkTo(methodOn(UserController.class).getUserAddresses(userId)).withRel("addresses");
		
		//Link userLink = linkTo(UserController.class).slash(userId).withRel("user");
		Link userLink = linkTo(methodOn(UserController.class).getUser(userId)).withRel("user");
		
		AddressRest returnValue = new ModelMapper().map(addressDto, AddressRest.class);
		
		returnValue.add(addressLink);
		returnValue.add(addressesLink);
		returnValue.add(userLink);
				
		return new EntityModel<>(returnValue);
		
	}
	

}
