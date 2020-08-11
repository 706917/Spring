package lab.alex.todo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lab.alex.todo.dto.UserDto;
import lab.alex.todo.io.entity.EventEntity;
import lab.alex.todo.io.entity.GroupEntity;
import lab.alex.todo.io.entity.UserEntity;
import lab.alex.todo.io.repository.EventRepository;
import lab.alex.todo.io.repository.GroupRepository;
import lab.alex.todo.io.repository.UserRepository;
import lab.alex.todo.services.UserService;
import lab.alex.todo.ui.model.request.UserDetailsRequestModel;
import lab.alex.todo.ui.model.responce.UserResponceModel;
import lab.alex.todo.utility.Utils;

@Service
public class UserServiceImpl implements UserService {

	// ********************* DJs *********************

	@Autowired
	UserRepository userRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	Utils utils;
	@Autowired
	ModelMapper mapper;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	// **************** Methods ***************

	@Override
	public UserDto createUser(UserDto userDto) throws Exception {

		UserDto returnValue = new UserDto();

		if (userRepository.findByEmail(userDto.getEmail()) != null)
			throw new Exception("User already exists!");

		UserEntity newUser = mapper.map(userDto, UserEntity.class);

		// Set Public_User_Id and encrypt users password
		String publicUserId = utils.generatePublicId();
		String encryptedPassword = passwordEncoder.encode(userDto.getPassword());
		newUser.setPublicUserId(publicUserId);
		newUser.setEncryptedPassword(encryptedPassword);

		// Save new user to DB
		UserEntity createdUser = userRepository.save(newUser);

		// **************** Create Private Group and Event *****************
		// Create new Private group
		GroupEntity newPrivateGroup = new GroupEntity();
		// Set the Admin Id
		newPrivateGroup.setAdminDetails(userRepository.findByPublicUserId(publicUserId));
		// Set the name as Private
		newPrivateGroup.setNameGroup("Private");
		// Set public id
		newPrivateGroup.setPublicGroupId(utils.generatePublicId());
		// Save new Group to DB
		GroupEntity createdGroup = groupRepository.save(newPrivateGroup);
		
		// Create Private Event
		EventEntity newPrivatEvent = new EventEntity();
		// Set the Admin Id
		newPrivatEvent.setCreatorDetails(userRepository.findByPublicUserId(publicUserId));
		// Set Group Details
		newPrivatEvent.setEventGroupDetails(createdGroup);
		// set name as Private
		newPrivatEvent.setNameEvent("Private");
		// Set Public_Event_Id
		newPrivatEvent.setPublicEventId(utils.generatePublicId());
		// Save new Event to DB
		EventEntity createdEvent = eventRepository.save(newPrivatEvent);
		
		// Add created Event to the List of Events in the cretedGroup
		List<EventEntity> listEvents = new ArrayList<>();
		listEvents.add(createdEvent);
		createdGroup.setGroupEvents(listEvents);
		// Update created Group
		createdGroup = groupRepository.save(createdGroup);
		
		// Add created Group and Event to the lists of groups and events of this user
		createdUser.setUsersEvents(listEvents);
		List<GroupEntity> listGroups = new ArrayList<>();
		listGroups.add(createdGroup);
		createdUser.setAdminGroups(listGroups);
		
		// Update createdUser, convert and send back
		createdUser = userRepository.save(createdUser);
		returnValue = mapper.map(createdUser, UserDto.class);
		return returnValue;
	}

	@Override
	public List<UserDto> getAllUsers() {

		List<UserDto> returnList = new ArrayList<>();

		userRepository.findAll().forEach(el -> returnList.add(mapper.map(el, UserDto.class)));

		return returnList;

	}

	@Override
	public UserDto getUserByUserId(String id) throws Exception {

		if (userRepository.findByPublicUserId(id) == null)
			throw new Exception("No such user");

		UserDto returnValue = mapper.map(userRepository.findByPublicUserId(id), UserDto.class);

		return returnValue;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDetailsRequestModel) throws Exception {

		UserDto newUserDetails = mapper.map(userDetailsRequestModel, UserDto.class);

		UserEntity currentUserDetails = userRepository.findByPublicUserId(id);

		if (currentUserDetails == null)
			throw new Exception("No such user!!!");

		currentUserDetails.setName(newUserDetails.getName());
		currentUserDetails.setPhone(newUserDetails.getPhone());
		currentUserDetails.setUserMoto(newUserDetails.getUserMoto());

		UserEntity updatedUser = userRepository.save(currentUserDetails);

		newUserDetails = mapper.map(updatedUser, UserDto.class);

		return newUserDetails;
	}

	@Override
	public void deleteUser(String id) throws Exception {
		UserEntity user = userRepository.findByPublicUserId(id);
		if (user == null)
			throw new Exception("No such user");
		userRepository.delete(user);

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException("No User such with the email " + email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

}
