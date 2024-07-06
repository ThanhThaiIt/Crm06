package crm06.service;

import java.util.List;

import crm06.entity.ProjectEntity;
import crm06.entity.StatisticalTaskEntity;
import crm06.entity.StatusEntity;
import crm06.entity.TaskEntity;
import crm06.entity.TaskEntityLists;
import crm06.repository.ProjectRepository;
import crm06.repository.TaskRepository;

public class ProjectServiceImp implements ProjectService{
private	ProjectRepository projectRepository = new ProjectRepository();
private TaskRepository taskRepository = new TaskRepository();
	@Override
	public List<ProjectEntity> getAllProject() {
		// TODO Auto-generated method stub
		return projectRepository.getAllProject();
	}
	@Override
	public void addProject(ProjectEntity projectEntity) {
		projectRepository.addProject(projectEntity);
		
	}
	@Override
	public boolean deleteProject(int id) {
	return	projectRepository.deleteProjectById(id) >0;
		
	}
	@Override
	public ProjectEntity getProjectByiD(int id) {
		// TODO Auto-generated method stub
		return projectRepository.getProjectById(id);
	}
	@Override
	public void editProject(ProjectEntity projectEntity) {
		projectRepository.editRoleById(projectEntity);
		
	}
	@Override
	public List<ProjectEntity> swapProj(int id, List<ProjectEntity> list) {
		// TODO Auto-generated method stub
		return projectRepository.swapProjectToFirst(id, list);
	}
	@Override
	public List<StatusEntity> swapStat(int id, List<StatusEntity> list) {
		// TODO Auto-generated method stub
		return projectRepository.swapStatusToFirst(id, list);
	}
	@Override
	public List<TaskEntity> getListTaskByIdProject(int id) {
		// TODO Auto-generated method stub
		return taskRepository.getListTaskByIdProjectForDetail(id);
	}
	@Override
	public StatisticalTaskEntity getStatisticalTaskEntity(List<TaskEntity> taskEntities) {
		// TODO Auto-generated method stub
		return taskRepository.getStatisticalTaskEntity(taskEntities);
	}
	@Override
	public TaskEntityLists getTasksByStatus(List<TaskEntity> taskList) {
		// TODO Auto-generated method stub
		return taskRepository.getTasksByStatus(taskList);
	}
	@Override
	public List<TaskEntity> getAllListTask() {
		// TODO Auto-generated method stub
		return taskRepository.getAllListTask();
	}
	@Override
	public StatisticalTaskEntity demSoLuongTaskTask(List<TaskEntity> taskList) {
		
		return taskRepository.demSoLuongTaskTask(taskList);
	}

}
