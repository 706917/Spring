package alex.lab.photo.app.service.imp;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl.BeanSupplierContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import alex.lab.photo.app.exceptions.UserServiceException;
import alex.lab.photo.app.io.entity.UserEntity;
import alex.lab.photo.app.io.repositories.UserRepository;
import alex.lab.photo.app.service.UserService;
import alex.lab.photo.app.shared.Utils;
import alex.lab.photo.app.shared.dto.UserDto;
import alex.lab.photo.app.ui.model.responce.ErrorMessages;

@Service
public class UserServiceIml implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {

		// Check if user with such email already exist
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeException("Record already exist");

		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);

		// Generate public userId with the length = 30 characters
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);

		// Encrypt password provided by user - to store encrypted version in the
		// DataBase
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity storeduserDetails = userRepository.save(userEntity);

		UserDto returnValue = new UserDto();

		BeanUtils.copyProperties(storeduserDetails, returnValue);

		return returnValue;
	}
	
	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		
		UserDto returnValue = new UserDto();		
		UserEntity userEntity = userRepository.findByUserId(id);
		
		if(userEntity==null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName() );
		
		UserEntity updatedUser = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}
	

	@Override
	public UserDto getUser(String email) {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String id) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	

}
