package crm06.service;

import java.util.List;

import crm06.entity.RoleEntity;
import crm06.repository.RoleRepository;

public class RoleServiceImp  implements RoleService{
private RoleRepository roleRepository =  new RoleRepository();


	@Override
	public void addRole(RoleEntity roleEntity) {
		
		roleRepository.addRole(roleEntity);
	}


	@Override
	public List<RoleEntity> getAllRole() {
		// TODO Auto-generated method stub
		return roleRepository.getAllRoles();
	}


	

}
