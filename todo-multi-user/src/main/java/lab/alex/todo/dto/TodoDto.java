package lab.alex.todo.dto;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lab.alex.todo.io.entity.EventEntity;
import lab.alex.todo.io.entity.GroupEntity;
import lab.alex.todo.io.entity.UserEntity;

public class TodoDto {
	
	//********************Attributes***************
	
	private long databaseTodoId;
	private String publicTodoId;

	private String textTodo;
	private Date dateTimeTodoCreated;
	private boolean completed;
	
	
	private String public_user_id;
	private String public_event_id;
	private String public_group_id;
	
	
	
	private UserEntity creatorDetails;	
	private EventEntity eventDetails;
	private GroupEntity todoGroupDetails;


	//****************Accessors************************	
	

	public long getDatabaseTodoId() {
		return databaseTodoId;
	}
	public void setDatabaseTodoId(long databaseTodoId) {
		this.databaseTodoId = databaseTodoId;
	}
	
	public String getPublicTodoId() {
		return publicTodoId;
	}
	public void setPublicTodoId(String publicTodoId) {
		this.publicTodoId = publicTodoId;
	}
	public String getTextTodo() {
		return textTodo;
	}
	public void setTextTodo(String textTodo) {
		this.textTodo = textTodo;
	}
	public Date getDateTimeTodoCreated() {
		return dateTimeTodoCreated;
	}
	public void setDateTimeTodoCreated(Date dateTimeTodoCreated) {
		this.dateTimeTodoCreated = dateTimeTodoCreated;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public String getPublic_user_id() {
		return public_user_id;
	}
	public void setPublic_user_id(String public_user_id) {
		this.public_user_id = public_user_id;
	}
	public String getPublic_event_id() {
		return public_event_id;
	}
	public void setPublic_event_id(String public_event_id) {
		this.public_event_id = public_event_id;
	}
	public String getPublic_group_id() {
		return public_group_id;
	}
	public void setPublic_group_id(String public_group_id) {
		this.public_group_id = public_group_id;
	}
	public UserEntity getCreatorDetails() {
		return creatorDetails;
	}
	public void setCreatorDetails(UserEntity creatorDetails) {
		this.creatorDetails = creatorDetails;
	}
	public EventEntity getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(EventEntity eventDetails) {
		this.eventDetails = eventDetails;
	}
	public GroupEntity getTodoGroupDetails() {
		return todoGroupDetails;
	}
	public void setTodoGroupDetails(GroupEntity todoGroupDetails) {
		this.todoGroupDetails = todoGroupDetails;
	}
	
	
	

}
