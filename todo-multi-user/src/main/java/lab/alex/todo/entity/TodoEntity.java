package lab.alex.todo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity(name = "todos")
public class TodoEntity implements Serializable {
	
	private static final long serialVersionUID = -7125683743784846300L;
	
	
	//********************Attributes***************
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long databaseTodoId;
	
	@Column (nullable = false)
	private String publicTodoId;
	
	@Column (nullable = false, length = 150)
	private String textTodo;
	
	@Column (nullable = false, length = 30)
	@JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss, timezone = UTC")
	private Date dateTimeTodoCreated;
	
	@Column (nullable = false)
	private boolean completed;
	
	
	//***************Association****************
	@ManyToOne
	@JoinColumn(name = "publicUserId")
	private UserEntity userDetails;
	
	
	//*************Accessors*******************


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


	public UserEntity getUserDetails() {
		return userDetails;
	}


	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}
	
	
	
	
	
	
	

}