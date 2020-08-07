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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "groups_table")
public class GroupEntity implements Serializable{
	
	private static final long serialVersionUID = 3853540575654884477L;
	
	// *************** Attributes **********************
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long databaseGroupId;

	@Column(nullable = false)
	private String publicGroupId;

	@Column(nullable = false, length = 50)
	private String nameGroup;

	@Column(nullable = false, length = 100)
	private String descriptionGroup;

	
	
	
	
	
	//*****************Association*******************
		
	@OneToMany(mappedBy = "eventGroupDetails", cascade = CascadeType.ALL)
	private List<EventEntity> groupEvents;
	
	@OneToMany(mappedBy = "todoGroupDetails", cascade = CascadeType.ALL)
	private List<TodoEntity> groupTodos;
	
	
	//---------------
	
	@ManyToOne
	@JoinColumn(name = "public_user_id")
	private UserEntity adminDetails;
	
	//---------------
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "setGroups")
	private Set<UserEntity> setUsers = new HashSet<>();
	
	


	//****************Accessors************************

	public long getDatabaseGroupId() {
		return databaseGroupId;
	}

	public void setDatabaseGroupId(long databaseGroupId) {
		this.databaseGroupId = databaseGroupId;
	}

	public String getPublicGroupId() {
		return publicGroupId;
	}

	public void setPublicGroupId(String publicGroupId) {
		this.publicGroupId = publicGroupId;
	}

	public String getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	public String getDescriptionGroup() {
		return descriptionGroup;
	}

	public void setDescriptionGroup(String descriptionGroup) {
		this.descriptionGroup = descriptionGroup;
	}

	public List<EventEntity> getGroupEvents() {
		return groupEvents;
	}

	public void setGroupEvents(List<EventEntity> groupEvents) {
		this.groupEvents = groupEvents;
	}

	public List<TodoEntity> getGroupTodos() {
		return groupTodos;
	}

	public void setGroupTodos(List<TodoEntity> groupTodos) {
		this.groupTodos = groupTodos;
	}

	public UserEntity getAdminDetails() {
		return adminDetails;
	}

	public void setAdminDetails(UserEntity adminDetails) {
		this.adminDetails = adminDetails;
	}

	public Set<UserEntity> getSetUsers() {
		return setUsers;
	}

	public void setSetUsers(Set<UserEntity> setUsers) {
		this.setUsers = setUsers;
	}

	
	
	
	

}
