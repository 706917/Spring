package lab.alex.todo.io.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "users_table")
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
	
	@Column (nullable = false, length = 150)
	private String encryptedPassword;
	
	
	
	//***************** ASSOCIATIONS *******************
	
	@OneToMany(mappedBy = "creatorDetails", cascade = CascadeType.ALL)
	private List<TodoEntity> usersTodos;
	
	@OneToMany(mappedBy = "adminDetails", cascade = CascadeType.ALL)
	private List<GroupEntity> adminGroups;
	
	@OneToMany(mappedBy = "creatorDetails", cascade = CascadeType.ALL)
	private List<EventEntity> usersEvents;
	
	
	// -------------------many_to_many----------------------
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "event_users",
		joinColumns = {@JoinColumn(name = "event_id")},
		inverseJoinColumns = {@JoinColumn(name = "user_id")}	)
	private Set <EventEntity> setEvents = new HashSet<>();
	

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "group_users",
		joinColumns = {@JoinColumn(name = "group_id")},
		inverseJoinColumns = {@JoinColumn(name = "user_id")}	)
	private Set <GroupEntity> setGroups = new HashSet<>();
	
	


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


	public List<GroupEntity> getAdminGroups() {
		return adminGroups;
	}


	public void setAdminGroups(List<GroupEntity> adminGroups) {
		this.adminGroups = adminGroups;
	}


	public List<EventEntity> getUsersEvents() {
		return usersEvents;
	}


	public void setUsersEvents(List<EventEntity> usersEvents) {
		this.usersEvents = usersEvents;
	}


	public Set<EventEntity> getSetEvents() {
		return setEvents;
	}


	public void setSetEvents(Set<EventEntity> setEvents) {
		this.setEvents = setEvents;
	}


	public Set<GroupEntity> getSetGroups() {
		return setGroups;
	}


	public void setSetGroups(Set<GroupEntity> setGroups) {
		this.setGroups = setGroups;
	}
	
	
}
