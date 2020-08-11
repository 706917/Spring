package lab.alex.todo.ui.model.responce;

import java.util.Date;
import java.util.List;

public class EventResponceModel {
	
	//********************Attributes***************
	
	
	private String publicEventId;
	private String nameEvent;
	private String description;
	private Date dateTimeEventCreated;
	
	
	
	//*****************Association*******************

	private List<TodoResponceModel> eventTodos;
	
	//---------------------------
	
	private String public_user_id;
	private String public_group_id;
	
	

	//****************Accessors************************
	
	public String getPublicEventId() {
		return publicEventId;
	}
	public void setPublicEventId(String publicEventId) {
		this.publicEventId = publicEventId;
	}
	public String getNameEvent() {
		return nameEvent;
	}
	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateTimeEventCreated() {
		return dateTimeEventCreated;
	}
	public void setDateTimeEventCreated(Date dateTimeEventCreated) {
		this.dateTimeEventCreated = dateTimeEventCreated;
	}
	public List<TodoResponceModel> getEventTodos() {
		return eventTodos;
	}
	public void setEventTodos(List<TodoResponceModel> eventTodos) {
		this.eventTodos = eventTodos;
	}
	public String getPublic_user_id() {
		return public_user_id;
	}
	public void setPublic_user_id(String public_user_id) {
		this.public_user_id = public_user_id;
	}
	public String getPublic_group_id() {
		return public_group_id;
	}
	public void setPublic_group_id(String public_group_id) {
		this.public_group_id = public_group_id;
	}
	
	
	

}
