package lab.alex.todo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.alex.todo.dto.UserDto;
import lab.alex.todo.entity.UserEntity;
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
			throw new Exception("User already exist!");

		UserEntity newUser = mapper.map(userDto, UserEntity.class);

		String publicUserId = utils.generatePublicUserId();
		String encryptedPassword = "example";

		newUser.setPublicUserId(publicUserId);
		newUser.setEncryptedPassword(encryptedPassword);

		UserEntity createdUser = userRepository.save(newUser);

		returnValue = mapper.map(createdUser, UserDto.class);

		return returnValue;
	}

}
