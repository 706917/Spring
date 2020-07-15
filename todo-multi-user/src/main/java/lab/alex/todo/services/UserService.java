package lab.alex.todo.services;

import lab.alex.todo.dto.UserDto;

public interface UserService {// extends UserDetailsService{
	
	UserDto createUser(UserDto userDto) throws Exception;

}
