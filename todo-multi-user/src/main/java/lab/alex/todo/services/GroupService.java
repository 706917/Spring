package lab.alex.todo.services;


import lab.alex.todo.dto.GroupDto;

public interface GroupService {

	GroupDto createGroup (String userId, GroupDto groupDto);
	
	
	

}
