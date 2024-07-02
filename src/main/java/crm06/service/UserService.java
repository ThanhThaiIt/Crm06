package crm06.service;

import java.util.List;

import crm06.entity.RoleEntity;
import crm06.entity.UserEntity;

public interface UserService {
	List<RoleEntity> getAllRole();
	List<UserEntity> getAllUserEntity();
	
	public void addUser(UserEntity userEntity);
	boolean deleteUser(int id);
	public UserEntity getUserById(int id) ;
	public void editUser(UserEntity userEntity);
}
