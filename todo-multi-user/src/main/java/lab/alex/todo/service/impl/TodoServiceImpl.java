package lab.alex.todo.service.impl;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab.alex.todo.dto.TodoDto;
import lab.alex.todo.io.entity.TodoEntity;
import lab.alex.todo.io.repository.EventRepository;
import lab.alex.todo.io.repository.GroupRepository;
import lab.alex.todo.io.repository.TodoRepository;
import lab.alex.todo.io.repository.UserRepository;
import lab.alex.todo.services.TodoService;
import lab.alex.todo.utility.Utils;

@Service
public class TodoServiceImpl implements TodoService {
	
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

	@Override
	public TodoDto createTodo(String userId, TodoDto todoDto) {

		TodoEntity newTodo = mapper.map(todoDto, TodoEntity.class);
		
		// ************ Set the foreign keys for many-to-one relationship *******************
		newTodo.setCreatorDetails(userRepository.findByPublicUserId(userId));
		newTodo.setEventDetails(eventRepository.findByPublicEventId(todoDto.getPublic_event_id()));
		newTodo.setTodoGroupDetails(groupRepository.findByPublicGroupId(todoDto.getPublic_group_id()));
		
		// Set PublicTodoId value
		
		newTodo.setPublicTodoId(utils.generatePublicId());
		
		// Save new Todo to DB
		TodoEntity createdTodo = todoRepository.save(newTodo);
		
		// Convert and send back
		TodoDto returnValue = mapper.map(createdTodo, TodoDto.class);
		
		return returnValue;
	}
	

}
