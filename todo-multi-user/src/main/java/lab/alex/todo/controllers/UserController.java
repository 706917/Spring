package lab.alex.todo.controllers;

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
import lab.alex.todo.model.request.UserDetailsRequestModel;
import lab.alex.todo.model.responce.UserResponceModel;
import lab.alex.todo.services.UserService;

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
	public String getAllUsers(){
		
		return "Alex";
		
	}
	
	@GetMapping("/{id}")
	public String getUser(@PathVariable String id){
		
		return "Alex " + id;
		
	}
	
	@PostMapping
	public UserResponceModel createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) throws Exception {
		
		UserDto userDto = mapper.map(userDetailsRequestModel, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		
		UserResponceModel returnValue = mapper.map(createdUser, UserResponceModel.class);
		
		return returnValue;
	}
	
	@PutMapping("/{id}")
	public String updateUser(@PathVariable String id) {
		return "Updated";
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable String id) {
		return "deleted";
	}

}
