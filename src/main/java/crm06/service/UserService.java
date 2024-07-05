package crm06.service;

import java.util.List;

import crm06.entity.RoleEntity;
import crm06.entity.StatisticalTaskEntity;
import crm06.entity.TaskEntity;
import crm06.entity.TaskEntityLists;
import crm06.entity.UserEntity;

public interface UserService {
	List<RoleEntity> getAllRole();
	List<UserEntity> getAllUserEntity();
	
	public void addUser(UserEntity userEntity);
	boolean deleteUser(int id);
	public UserEntity getUserById(int id) ;
	public void editUser(UserEntity userEntity);
	public List<UserEntity> swapUser(int id, List<UserEntity> listUsers) ;
	public List<TaskEntity> getListTaskByidUser(int id) ;
	public StatisticalTaskEntity getStatisticalTaskEntity(List<TaskEntity> listTask) ;
	public TaskEntityLists getTasksByStatus(List<TaskEntity> taskList) ;
}
