package crm06.service;

import java.util.List;

import crm06.entity.RoleEntity;

public interface RoleService {
	public void addRole(RoleEntity roleEntity);

	public List<RoleEntity> getAllRole();
}
