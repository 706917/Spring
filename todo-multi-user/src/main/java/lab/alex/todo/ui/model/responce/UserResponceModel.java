package lab.alex.todo.ui.model.responce;

import java.util.List;

public class UserResponceModel {
	
	

	//******************Attributes********************

	private String publicUserId;

	private String name;
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

	public String getName() {
		return name;
	}

	public void setName(String userName) {
		this.name = userName;
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
