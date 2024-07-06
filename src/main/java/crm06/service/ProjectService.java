package crm06.service;

import java.util.List;

import crm06.entity.ProjectEntity;
import crm06.entity.StatisticalTaskEntity;
import crm06.entity.StatusEntity;
import crm06.entity.TaskEntity;
import crm06.entity.TaskEntityLists;

public interface ProjectService {
public List<ProjectEntity> getAllProject() ;
public void addProject(ProjectEntity projectEntity) ;
public boolean deleteProject(int id) ;
public ProjectEntity getProjectByiD(int id) ;
public void editProject(ProjectEntity projectEntity) ;
public List<ProjectEntity>  swapProj(int id, List<ProjectEntity> list) ;
public List<StatusEntity>  swapStat(int id, List<StatusEntity> list) ;
public List<TaskEntity> getListTaskByIdProject(int id) ;
public StatisticalTaskEntity getStatisticalTaskEntity(List<TaskEntity> taskEntities) ;
public TaskEntityLists getTasksByStatus(List<TaskEntity> taskList);
public List<TaskEntity> getAllListTask() ;
public  StatisticalTaskEntity demSoLuongTaskTask(List<TaskEntity> taskList);
}
