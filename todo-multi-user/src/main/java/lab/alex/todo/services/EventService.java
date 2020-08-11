package lab.alex.todo.services;


import lab.alex.todo.dto.EventDto;

public interface EventService {


	EventDto createEvent(String userId, EventDto eventDto);
	
	

}
