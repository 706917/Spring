package alex.lab.photo.app.ui.service.imp;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import alex.lab.photo.app.io.entity.UserEntity;
import alex.lab.photo.app.io.repositories.UserRepository;
import alex.lab.photo.app.shared.Utils;
import alex.lab.photo.app.shared.dto.UserDto;
import alex.lab.photo.app.ui.service.UserService;

@Service
public class UserServiceIml implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	public UserDto createUser(UserDto user) {
		
		// Check if user with such email already exist		
		if (userRepository.findByEmail(user.getEmail()) !=null) throw new RuntimeException("Record already exist");
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		
		// Generate public userId with the length = 30 characters
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);
		
		// Encrypt password provided by user - to store encrypted version in the DataBase
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		
		UserEntity storeduserDetails = userRepository.save(userEntity);
		
		UserDto returnValue = new UserDto();
		
		BeanUtils.copyProperties(storeduserDetails, returnValue);
			
		return returnValue;
	}
	
	@Override
	public UserDto getUser(String email) {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);		
		
		UserDto returnValue = new UserDto();		
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
		
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList());
	}

}
