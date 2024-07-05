package crm06.entity;

import java.sql.Timestamp;

public class TaskEntity {
	private int id;
	private UserEntity userEntity;
	private ProjectEntity projectEntity;
	private StatusEntity statusEntity;
	private String name;
	private Timestamp start_date;
	private Timestamp end_date;

	public TaskEntity(int id, int userID, int projectID, int statusID, String name, Timestamp start_date,
			Timestamp end_date) {
		super();
		this.id = id;
//		this.userEntity = userEntity;
//		this.projectEntity = projectEntity;
//		this.statusEntity = statusEntity;
		this.userEntity = new UserEntity();
		this.userEntity.setId(userID);
		this.projectEntity = new ProjectEntity();
		this.projectEntity.setId(projectID);
		this.statusEntity = new StatusEntity();
		this.statusEntity.setId(statusID);
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	public TaskEntity(int id, String userName, String projectName, String statusName, String name, Timestamp start_date,
			Timestamp end_date) {
		super();
		this.id = id;
//		this.userEntity = userEntity;
//		this.projectEntity = projectEntity;
//		this.statusEntity = statusEntity;
		this.userEntity = new UserEntity();
		this.userEntity.setlName(userName);
		this.projectEntity = new ProjectEntity();
		this.projectEntity.setName(projectName);
		this.statusEntity = new StatusEntity();
		this.statusEntity.setName(statusName);
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public TaskEntity(int userID, int projectID, int statusID, String name, Timestamp start_date, Timestamp end_date) {
		super();

//		this.userEntity = userEntity;
//		this.projectEntity = projectEntity;
//		this.statusEntity = statusEntity;
		this.userEntity = new UserEntity();
		this.userEntity.setId(userID);
		this.projectEntity = new ProjectEntity();
		this.projectEntity.setId(projectID);
		this.statusEntity = new StatusEntity();
		this.statusEntity.setId(statusID);
		this.name = name;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public ProjectEntity getProjectEntity() {
		return projectEntity;
	}

	public void setProjectEntity(ProjectEntity projectEntity) {
		this.projectEntity = projectEntity;
	}

	public StatusEntity getStatusEntity() {
		return statusEntity;
	}

	public void setStatusEntity(StatusEntity statusEntity) {
		this.statusEntity = statusEntity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getStart_date() {
		return start_date;
	}

	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	public Timestamp getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}

}
