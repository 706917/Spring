package lab.alex.todo.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lab.alex.todo.dto.EventDto;
import lab.alex.todo.io.entity.EventEntity;
import lab.alex.todo.io.repository.EventRepository;
import lab.alex.todo.io.repository.GroupRepository;
import lab.alex.todo.io.repository.TodoRepository;
import lab.alex.todo.io.repository.UserRepository;
import lab.alex.todo.services.EventService;
import lab.alex.todo.utility.Utils;

public class EventServiceImpl implements EventService {
	
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
	
// ************************* Create New Event *************************
	@Override
	public EventDto createEvent(String userId, EventDto eventDto) {

		EventEntity newEvent = mapper.map(eventDto, EventEntity.class);		

		// ************ Set the foreign keys for many-to-one relationship *******************
		// Set the Admin Id
		newEvent.setCreatorDetails(userRepository.findByPublicUserId(userId));
		// Set Group Id
		newEvent.setEventGroupDetails(groupRepository.findByPublicGroupId(eventDto.getPublic_group_id()));
		
		// Set Public_Event_Id
		newEvent.setPublicEventId(utils.generatePublicId());
		
		// Save new Event to DB
		EventEntity createdEvent = eventRepository.save(newEvent);
		
		// Convert Entity to Dto and send back		
		EventDto returnValue = mapper.map(createdEvent, EventDto.class);	
		
		return returnValue;
	}
	
	

}
