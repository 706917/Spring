package lab.alex.todo.io.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


@Entity
@Table(name="events_table")
public class EventEntity implements Serializable{

	private static final long serialVersionUID = 2697897580625213781L;
	
	
	//******************Attributes********************

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long databaseEventId;

		@Column(nullable = false)
		private String publicEventId;

		@Column(nullable = false, length = 50)
		private String nameEvent;

		@Column(nullable = false, length = 100)
		private String description;

		@Column (nullable = false, length = 30)
		@JsonFormat(shape = Shape.STRING, pattern = "dd-MM HH:mm, timezone = UTC")
		private Date dateTimeEventCreated;
		
		
		
		//*****************Association*******************
		@OneToMany(mappedBy = "eventDetails", cascade = CascadeType.ALL)
		private List<TodoEntity> eventTodos;
		
		//---------------------------
		
		@ManyToOne
		@JoinColumn(name = "public_user_id")
		private UserEntity creatorDetails;
		
		@ManyToOne
		@JoinColumn(name = "public_group_id")
		private GroupEntity eventGroupDetails;
		
		//---------------------------------
		
		@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "setEvents")
		private Set <UserEntity> setUsers = new HashSet<>();
		
		


		//****************Accessors************************

		public long getDatabaseEventId() {
			return databaseEventId;
		}

		public void setDatabaseEventId(long databaseEventId) {
			this.databaseEventId = databaseEventId;
		}

		public String getPublicEventId() {
			return publicEventId;
		}

		public void setPublicEventId(String publicEventId) {
			this.publicEventId = publicEventId;
		}

		public String getNameEvent() {
			return nameEvent;
		}

		public void setNameEvent(String nameEvent) {
			this.nameEvent = nameEvent;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getDateTimeEventCreated() {
			return dateTimeEventCreated;
		}

		public void setDateTimeEventCreated(Date dateTimeEventCreated) {
			this.dateTimeEventCreated = dateTimeEventCreated;
		}
//
		public List<TodoEntity> getEventTodos() {
			return eventTodos;
		}

		public void setEventTodos(List<TodoEntity> eventTodos) {
			this.eventTodos = eventTodos;
		}

		public UserEntity getCreatorDetails() {
			return creatorDetails;
		}

		public void setCreatorDetails(UserEntity creatorDetails) {
			this.creatorDetails = creatorDetails;
		}

		public GroupEntity getEventGroupDetails() {
			return eventGroupDetails;
		}

		public void setEventGroupDetails(GroupEntity eventGroupDetails) {
			this.eventGroupDetails = eventGroupDetails;
		}

		public Set<UserEntity> getSetUsers() {
			return setUsers;
		}

		public void setSetUsers(Set<UserEntity> setUsers) {
			this.setUsers = setUsers;
		}
		
		
		
		
		

}
