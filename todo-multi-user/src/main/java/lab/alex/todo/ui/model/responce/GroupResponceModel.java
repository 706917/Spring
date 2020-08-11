package lab.alex.todo.ui.model.responce;

import java.util.List;
import java.util.Set;

public class GroupResponceModel {

	// *************** Attributes **********************

	private String publicGroupId;
	private String nameGroup;
	private String descriptionGroup;
	// AdminId
	private String public_user_id;

	// *****************Association*******************

	private List<EventResponceModel> groupEvents;
	private List<TodoResponceModel> groupTodos;
	private Set<UserResponceModel> setUsers;

	// ****************Accessors************************

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

	public List<EventResponceModel> getGroupEvents() {
		return groupEvents;
	}

	public void setGroupEvents(List<EventResponceModel> groupEvents) {
		this.groupEvents = groupEvents;
	}

	public List<TodoResponceModel> getGroupTodos() {
		return groupTodos;
	}

	public void setGroupTodos(List<TodoResponceModel> groupTodos) {
		this.groupTodos = groupTodos;
	}

	public Set<UserResponceModel> getSetUsers() {
		return setUsers;
	}

	public void setSetUsers(Set<UserResponceModel> setUsers) {
		this.setUsers = setUsers;
	}

}
