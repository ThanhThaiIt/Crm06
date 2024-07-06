package crm06.service;

import java.util.List;

import crm06.entity.ProjectEntity;
import crm06.entity.StatusEntity;
import crm06.entity.TaskEntity;
import crm06.entity.UserEntity;
import crm06.repository.ProjectRepository;
import crm06.repository.TaskRepository;
import crm06.repository.UserRepository;

public class TaskServiceImp implements TaskService {
	UserRepository userRepository = new UserRepository();
	ProjectRepository projectRepository = new ProjectRepository();
	TaskRepository taskRepository = new TaskRepository();

	@Override
	public List<StatusEntity> getAllListStatus() {
		// TODO Auto-generated method stub
		return taskRepository.getAllStattus();
	}

	@Override
	public List<ProjectEntity> getAllListProject() {
		// TODO Auto-generated method stub
		return projectRepository.getAllProject();
	}

	@Override
	public List<UserEntity> getAllListUser() {
		// TODO Auto-generated method stub
		return userRepository.getAllUser();
	}

	@Override
	public void addTask(TaskEntity taskEntity) {
		taskRepository.addTask(taskEntity);
		
	}

	@Override
	public List<TaskEntity> getAllTask() {
		// TODO Auto-generated method stub
		return taskRepository.getAllTask();
	}

	@Override
	public boolean deleteTask(int id) {
		// TODO Auto-generated method stub
		return taskRepository.deleteTaskById(id)>0;
	}

	@Override
	public TaskEntity getTaskByID(int id) {
		return taskRepository.getTaskById(id);
		
	}

	@Override
	public void editTask(TaskEntity taskEntity) {
		taskRepository.editTaskById(taskEntity);
		
	}

	@Override
	public List<TaskEntity> DisplayAllTaskLimit(int start, int count) {
		// TODO Auto-generated method stub
		return taskRepository.DisplayAllTaskLimit(start, count);
	}

	@Override
	public int countRows() {
		// TODO Auto-generated method stub
		return taskRepository.countRows();
	}
	

}
