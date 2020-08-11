package lab.alex.todo.ui.model.responce;

import java.util.Date;

public class TodoResponceModel {
	
	//********************Attributes***************

		private String publicTodoId;

		private String textTodo;

		private Date dateTimeTodoCreated;

		private boolean completed;
		
		private String public_user_id;
		private String public_event_id;
		private String public_group_id;
		
		
		//*************Accessors*******************

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

}
