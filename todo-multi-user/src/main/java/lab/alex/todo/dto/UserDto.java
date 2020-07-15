package lab.alex.todo.dto;

import java.io.Serializable;
import java.util.List;


import lab.alex.todo.entity.TodoEntity;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -4403819017131408502L;

	// ******************Attributes********************

	private long databaseUserId;

	private String publicUserId;

	private String name;
	private String phone;
	private String email;

	private String userMoto;

	private String password;
	private String encryptedPassword;

	// *****************Association*******************

	private List<TodoEntity> usersTodos;
	
	// **************** Accessors **********************

	public long getDatabaseUserId() {
		return databaseUserId;
	}

	public void setDatabaseUserId(long databaseUserId) {
		this.databaseUserId = databaseUserId;
	}

	public String getPublicUserId() {
		return publicUserId;
	}

	public void setPublicUserId(String publicUserId) {
		this.publicUserId = publicUserId;
	}

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

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public List<TodoEntity> getUsersTodos() {
		return usersTodos;
	}

	public void setUsersTodos(List<TodoEntity> usersTodos) {
		this.usersTodos = usersTodos;
	}

}
