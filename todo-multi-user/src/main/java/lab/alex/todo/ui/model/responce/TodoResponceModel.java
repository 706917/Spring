package lab.alex.todo.ui.model.responce;

import java.util.Date;

public class TodoResponceModel {
	
	//********************Attributes***************

		private String publicTodoId;

		private String textTodo;

		private Date dateTimeTodoCreated;

		private boolean completed;
		
		
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

}
