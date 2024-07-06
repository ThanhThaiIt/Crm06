package crm06.service;

import java.util.List;

import crm06.entity.ProjectEntity;
import crm06.entity.StatusEntity;
import crm06.entity.TaskEntity;
import crm06.entity.UserEntity;

public interface TaskService {
public List<StatusEntity> getAllListStatus() ;
public List<ProjectEntity> getAllListProject() ;
public List<UserEntity> getAllListUser() ;
public void addTask(TaskEntity taskEntity);
public List<TaskEntity> getAllTask() ;
public boolean deleteTask(int id) ;
public TaskEntity getTaskByID(int id) ;
public void editTask(TaskEntity taskEntity) ;
public  List<TaskEntity> DisplayAllTaskLimit(int start, int count);
public  int countRows();
}
