package crm06.entity;

import java.util.List;

public class TaskEntityLists {

	private List<TaskEntity> chuaThucHienTasks;
	private List<TaskEntity> dangThucHienTasks;
	private List<TaskEntity> daThucHienTasks;

	public TaskEntityLists(List<TaskEntity> chuaThucHienTasks, List<TaskEntity> dangThucHienTasks,
			List<TaskEntity> daThucHienTasks) {
		super();
		this.chuaThucHienTasks = chuaThucHienTasks;
		this.dangThucHienTasks = dangThucHienTasks;
		this.daThucHienTasks = daThucHienTasks;
	}

	public TaskEntityLists() {
		super();
	}

	public List<TaskEntity> getChuaThucHienTasks() {
		return chuaThucHienTasks;
	}

	public void setChuaThucHienTasks(List<TaskEntity> chuaThucHienTasks) {
		this.chuaThucHienTasks = chuaThucHienTasks;
	}

	public List<TaskEntity> getDangThucHienTasks() {
		return dangThucHienTasks;
	}

	public void setDangThucHienTasks(List<TaskEntity> dangThucHienTasks) {
		this.dangThucHienTasks = dangThucHienTasks;
	}

	public List<TaskEntity> getDaThucHienTasks() {
		return daThucHienTasks;
	}

	public void setDaThucHienTasks(List<TaskEntity> daThucHienTasks) {
		this.daThucHienTasks = daThucHienTasks;
	}

}
