package lab.alex.todo.model.responce;

import java.util.List;

public class UserResponceModel {
	
	

	//******************Attributes********************

	private String publicUserId;

	private String userName;

	private String phone;

	private String email;

	private String userMoto;
	
	private List<TodoResponceModel> usersTodos;
	
	

	//****************Accessors************************

	public String getPublicUserId() {
		return publicUserId;
	}

	public void setPublicUserId(String publicUserId) {
		this.publicUserId = publicUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserMoto() {
		return userMoto;
	}

	public void setUserMoto(String userMoto) {
		this.userMoto = userMoto;
	}

	public List<TodoResponceModel> getUsersTodos() {
		return usersTodos;
	}

	public void setUsersTodos(List<TodoResponceModel> usersTodos) {
		this.usersTodos = usersTodos;
	}

}
