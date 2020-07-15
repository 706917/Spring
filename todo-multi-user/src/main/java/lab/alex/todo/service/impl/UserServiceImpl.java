package lab.alex.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.alex.todo.dto.UserDto;
import lab.alex.todo.entity.UserEntity;
import lab.alex.todo.model.request.UserDetailsRequestModel;
import lab.alex.todo.model.responce.UserResponceModel;
import lab.alex.todo.repository.UserRepository;
import lab.alex.todo.services.UserService;
import lab.alex.todo.utility.Utils;

@Service
public class UserServiceImpl implements UserService {

	// ********************* DJs *********************

	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	@Autowired
	ModelMapper mapper;

	// **************** Methods ***************

	@Override
	public UserDto createUser(UserDto userDto) throws Exception {
		

		UserDto returnValue = new UserDto();

		if (userRepository.findByEmail(userDto.getEmail()) != null)
			throw new Exception("User already exists!");

		UserEntity newUser = mapper.map(userDto, UserEntity.class);

		String publicUserId = utils.generatePublicUserId();
		String encryptedPassword = "example";

		newUser.setPublicUserId(publicUserId);
		newUser.setEncryptedPassword(encryptedPassword);

		UserEntity createdUser = userRepository.save(newUser);

		returnValue = mapper.map(createdUser, UserDto.class);

		return returnValue;
	}
	
	
	
	
	
	
	@Override
	public List<UserDto> getAllUsers(){
		
		List <UserDto> returnList = new ArrayList<>();
		
		userRepository.findAll().forEach(el -> returnList.add(mapper.map(el, UserDto.class)));
		
		return returnList;
		
	}



	@Override
	public UserDto getUserByUserId(String id) throws Exception {
		
		if (userRepository.findByPublicUserId(id) == null) throw new Exception("No such user");
		
		UserDto returnValue = mapper.map(userRepository.findByPublicUserId(id), UserDto.class);
		
		return returnValue;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDetailsRequestModel) throws Exception {

		UserDto newUserDetails = mapper.map(userDetailsRequestModel, UserDto.class);
		
		UserEntity currentUserDetails = userRepository.findByPublicUserId(id);
		
		if (currentUserDetails == null) throw new Exception ("No such user!!!");
		
		currentUserDetails.setName(newUserDetails.getName());
		currentUserDetails.setPhone(newUserDetails.getPhone());
		currentUserDetails.setUserMoto(newUserDetails.getUserMoto());
		
		UserEntity updatedUser = userRepository.save(currentUserDetails);
		
		newUserDetails = mapper.map(updatedUser, UserDto.class);
		
		
		return newUserDetails;
	}

}
