package lab.alex.todo.ui.model.request;

import java.util.List;
import java.util.Set;

import lab.alex.todo.dto.EventDto;
import lab.alex.todo.dto.TodoDto;
import lab.alex.todo.dto.UserDto;

public class GroupDetailsRequestModel {

	// *************** Attributes **********************

	private String nameGroup;
	private String descriptionGroup;
	// AdminId
	private String public_user_id;

	// ****************Accessors************************

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

}
