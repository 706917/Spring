package alex.lab.photo.app.ui.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import alex.lab.photo.app.shared.dto.UserDto;

public interface UserServise  extends UserDetailsService{
	
	UserDto createUser(UserDto user);

}
