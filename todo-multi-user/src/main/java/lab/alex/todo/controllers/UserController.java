package lab.alex.todo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@GetMapping
	public String getAllUsers(){
		
		return "Alex";
		
	}
	
	@GetMapping("/{id}")
	public String getUser(@PathVariable String id){
		
		return "Alex " + id;
		
	}
	
	@PostMapping("/{id}")
	public String createUser(@PathVariable String id) {
		return "Created";
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
