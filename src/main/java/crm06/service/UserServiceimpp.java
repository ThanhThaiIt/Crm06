package crm06.service;

import java.util.List;

import crm06.entity.RoleEntity;
import crm06.entity.StatisticalTaskEntity;
import crm06.entity.TaskEntity;
import crm06.entity.TaskEntityLists;
import crm06.entity.UserEntity;
import crm06.repository.RoleRepository;
import crm06.repository.TaskRepository;
import crm06.repository.UserRepository;

public class UserServiceimpp implements UserService {
	private RoleRepository roleRepository = new RoleRepository();
	private UserRepository userRepository = new UserRepository();
	private TaskRepository taskRepository = new TaskRepository();

	@Override
	public List<RoleEntity> getAllRole() {
		// TODO Auto-generated method stub
		return roleRepository.getAllRoles();
	}

	@Override
	public void addUser(UserEntity userEntity) {
		userRepository.addUser(userEntity);

	}

	@Override
	public List<UserEntity> getAllUserEntity() {
		// TODO Auto-generated method stub
		return userRepository.getAllUser();
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		System.out.println(id);
		return userRepository.deleteUserById(id) > 0;
	}

	@Override
	public UserEntity getUserById(int id) {
		// TODO Auto-generated method stub
		return userRepository.getUserById(id);
	}

	@Override
	public void editUser(UserEntity userEntity) {
		userRepository.editUserById(userEntity);

	}

	@Override
	public List<UserEntity> swapUser(int id, List<UserEntity> listUsers) {
		return userRepository.swapUserToFirst(id, listUsers);
		
	}

	@Override
	public List<TaskEntity> getListTaskByidUser(int id) {
		// TODO Auto-generated method stub
		return taskRepository.getListTaskByIdUserForDetail(id);
	}

	@Override
	public StatisticalTaskEntity getStatisticalTaskEntity(List<TaskEntity> listTask) {
		return taskRepository.getStatisticalTaskEntity(listTask);
	}

	@Override
	public TaskEntityLists getTasksByStatus(List<TaskEntity> taskList) {
		
		return taskRepository.getTasksByStatus(taskList);
	}

	@Override
	public List<UserEntity> DisplayAllUserLimit(int start, int count) {
		// TODO Auto-generated method stub
		return userRepository.DisplayAllUserLimit(start, count);
	}

	@Override
	public int countRows() {
		
		return userRepository.countRows();
	}

}
