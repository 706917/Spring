package lab.alex.todo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "users")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -7109290743784846300L;
	
	
	//******************Attributes********************

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long databaseUserId;

	@Column(nullable = false)
	private String publicUserId;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 10)
	private String phone;

	@Column(nullable = false, length = 20)
	private String email;

	@Column(nullable = false, length = 150)
	private String userMoto;
	
	@Column (nullable = false, length = 50)
	private String encryptedPassword;
	
	//*****************Association*******************
	
	@OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
	private List<TodoEntity> usersTodos;
	
	

	//****************Accessors************************
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
