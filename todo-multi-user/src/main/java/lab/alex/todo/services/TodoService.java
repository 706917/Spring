package lab.alex.todo.services;


import lab.alex.todo.dto.TodoDto;

public interface TodoService {

	TodoDto createTodo(String userId, TodoDto todoDto);
	
	

}
