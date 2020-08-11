package lab.alex.todo.dto;

import java.util.List;
import java.util.Set;

public class GroupDto {

	// *************** Attributes **********************

	private long databaseGroupId;
	private String publicGroupId;
	private String nameGroup;
	private String descriptionGroup;
	// AdminId
	private String public_user_id;

	// *****************Association*******************

	private List<EventDto> groupEvents;
	private List<TodoDto> groupTodos;
	private Set<UserDto> setUsers;

	// ****************Accessors************************

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

	public String getPublic_user_id() {
		return public_user_id;
	}

	public void setPublic_user_id(String public_user_id) {
		this.public_user_id = public_user_id;
	}

	public List<EventDto> getGroupEvents() {
		return groupEvents;
	}

	public void setGroupEvents(List<EventDto> groupEvents) {
		this.groupEvents = groupEvents;
	}

	public List<TodoDto> getGroupTodos() {
		return groupTodos;
	}

	public void setGroupTodos(List<TodoDto> groupTodos) {
		this.groupTodos = groupTodos;
	}

	public Set<UserDto> getSetUsers() {
		return setUsers;
	}

	public void setSetUsers(Set<UserDto> setUsers) {
		this.setUsers = setUsers;
	}

}
