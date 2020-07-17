package lab.alex.todo.ui.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.alex.todo.dto.UserDto;
import lab.alex.todo.services.UserService;
import lab.alex.todo.ui.model.request.UserDetailsRequestModel;
import lab.alex.todo.ui.model.responce.OperationStatusModel;
import lab.alex.todo.ui.model.responce.ResponceOperationName;
import lab.alex.todo.ui.model.responce.ResponceOperationStatus;
import lab.alex.todo.ui.model.responce.UserResponceModel;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	// *************** DJs *****************
	@Autowired
	ModelMapper mapper;
	@Autowired
	UserService userService;
	
	
	// **************** Controllers **************
	
	@GetMapping
	public List<UserResponceModel> getAllUsers(){
		
		List<UserResponceModel> returnList = new ArrayList<>();
		
		userService.getAllUsers().forEach(el -> returnList.add(mapper.map(el, UserResponceModel.class)));
		
		return returnList;
		
	}	
	
	
	@GetMapping("/{id}")
	public UserResponceModel getUser(@PathVariable String id) throws Exception{
		
		UserResponceModel returnvalue = mapper.map(userService.getUserByUserId(id), UserResponceModel.class);
		
		return returnvalue;
		
	}
	
	@PostMapping
	public UserResponceModel createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) throws Exception {
		
		UserDto userDto = mapper.map(userDetailsRequestModel, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		
		UserResponceModel returnValue = mapper.map(createdUser, UserResponceModel.class);
		
		return returnValue;
	}
	
	@PutMapping("/{id}")
	public UserResponceModel updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetailsRequestModel) throws Exception {
		
		UserDto updatedUser =  userService.updateUser(id, mapper.map(userDetailsRequestModel, UserDto.class));
		
		UserResponceModel returnValue = mapper.map(updatedUser, UserResponceModel.class);
		
		return returnValue;
	}
	
	
	@DeleteMapping("/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception {
		
		userService.deleteUser(id);
		
		OperationStatusModel returnValue = new OperationStatusModel();
		
		returnValue.setOperationName(ResponceOperationName.DELETE.name());
		returnValue.setOperationStatus(ResponceOperationStatus.SUCCESS.name());
		
		
		return returnValue;
	}

}
