package lab.alex.todo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lab.alex.todo.dto.GroupDto;
import lab.alex.todo.io.entity.GroupEntity;
import lab.alex.todo.io.repository.EventRepository;
import lab.alex.todo.io.repository.GroupRepository;
import lab.alex.todo.io.repository.TodoRepository;
import lab.alex.todo.io.repository.UserRepository;
import lab.alex.todo.services.GroupService;
import lab.alex.todo.utility.Utils;

public class GroupServiceImpl implements GroupService {
	@Autowired
	TodoRepository todoRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventRepository eventRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	ModelMapper mapper;
	@Autowired
	Utils utils;

	// ****************** Create new group *****************
	@Override
	public GroupDto createGroup(String userId, GroupDto groupDto) {

		GroupEntity newGroup = mapper.map(groupDto, GroupEntity.class);

		// ************ Set the foreign keys for many-to-one relationship *******************
		newGroup.setAdminDetails(userRepository.findByPublicUserId(userId));
		
		// Set Public_Group_Id value
		newGroup.setPublicGroupId(utils.generatePublicId());
		
		// Save new Group to DB
		GroupEntity createdGroup = groupRepository.save(newGroup);
		
		// Convert and send back
		GroupDto returnValue = mapper.map(createdGroup, GroupDto.class);		
		
		return returnValue;
	}

	
	
	

}
