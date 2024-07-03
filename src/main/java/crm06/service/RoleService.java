package crm06.service;

import java.util.List;

import crm06.entity.RoleEntity;
import crm06.entity.UserEntity;

public interface RoleService {
	public void addRole(RoleEntity roleEntity);

	public List<RoleEntity> getAllRole();
	
	public boolean deleteRole(int id);
	
	public RoleEntity getRoleByID(int id);
	
	public void editRole(RoleEntity roleEntity) ;
}
