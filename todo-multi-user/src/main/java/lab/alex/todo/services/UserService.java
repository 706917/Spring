package lab.alex.todo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import lab.alex.todo.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto userDto) throws Exception;
	
	List <UserDto> getAllUsers();
	
	UserDto getUserByUserId(String id) throws Exception;
	

	UserDto updateUser(String id, UserDto userDto) throws Exception;

	void deleteUser(String id) throws Exception;

}
