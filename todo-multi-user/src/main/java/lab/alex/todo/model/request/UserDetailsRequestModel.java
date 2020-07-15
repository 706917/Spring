package lab.alex.todo.model.request;


public class UserDetailsRequestModel {

	// ******************Attributes********************

	private String name;
	private String phone;
	private String email;

	private String userMoto;

	private String password;

	

	// **************** Accessors **********************
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
