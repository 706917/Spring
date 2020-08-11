package lab.alex.todo.ui.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.alex.todo.dto.TodoDto;
import lab.alex.todo.services.TodoService;
import lab.alex.todo.ui.model.request.TodoDetailsRequestModel;
import lab.alex.todo.ui.model.responce.TodoResponceModel;

@RestController
@RequestMapping ("/api")
public class TodoController {
	
	@Autowired
	ModelMapper mapper;
	@Autowired
	TodoService todoService;
	
	
	@PostMapping(value = "/users/{userId}/todos")
	public TodoResponceModel createTodo(@PathVariable String userId, @RequestBody TodoDetailsRequestModel todoRequestModel) {
		
		TodoDto todoDto = mapper.map(todoRequestModel, TodoDto.class);
		
		TodoDto createdTodo = todoService.createTodo(userId, todoDto);
		
		TodoResponceModel returnValue = mapper.map(createdTodo, TodoResponceModel.class);
		
		return returnValue;
	}
	
	

}
