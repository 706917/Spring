package alex.lab.photo.app.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import alex.lab.photo.app.shared.dto.AddressDto;
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
	public List<UserDto> getUsers(int page, int limit) {
		
		List<UserDto> returnValue = new ArrayList<UserDto>();
		
		if(page>0) page-=1;
		
		PageRequest pageabaleRequest = PageRequest.of(page, limit);		
		Page<UserEntity> userPage = userRepository.findAll(pageabaleRequest);
		
		List<UserEntity> users = userPage.getContent();
		
		for(UserEntity user: users) {
			UserDto userDto = new ModelMapper().map(user, UserDto.class);//new UserDto();
			//BeanUtils.copyProperties(user, userDto);
			returnValue.add(userDto);
		}
		
		return returnValue;
	}
	
	

	@Override
	public UserDto createUser(UserDto user) {

		// Check if user with such email already exist
		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new UserServiceException("Record already exist");
		
		for (int i = 0; i<user.getAddresses().size(); i++) {
			AddressDto address = user.getAddresses().get(i);
			address.setUserDetails(user);
			address.setAddressId(utils.generateAddressId(15));
			user.getAddresses().set(i, address);
		}

		// UserEntity userEntity = new UserEntity();
		// BeanUtils.copyProperties(user, userEntity);
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		// Generate public userId with the length = 30 characters
		String publicUserId = utils.generateUserId(30);
		userEntity.setUserId(publicUserId);

		// Encrypt password provided by user - to store encrypted version in the DataBase
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity storeduserDetails = userRepository.save(userEntity);

		UserDto returnValue = modelMapper.map(storeduserDetails, UserDto.class);

		// BeanUtils.copyProperties(storeduserDetails, returnValue);
		

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

	//	UserDto returnValue = new ModelMapper().map(userEntity, UserDto.class); // this one is not working
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

		//BeanUtils.copyProperties(userEntity, returnValue);
		returnValue = new ModelMapper().map(userEntity, UserDto.class);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}
	

	@Override
	public void deleteUser(String id) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if(userEntity==null) throw new UsernameNotFoundException(id);
		userRepository.delete(userEntity); 		
	}

	

	

}
