package crm06.service;

import java.util.List;

import crm06.entity.ProjectEntity;
import crm06.entity.StatusEntity;

public interface ProjectService {
public List<ProjectEntity> getAllProject() ;
public void addProject(ProjectEntity projectEntity) ;
public boolean deleteProject(int id) ;
public ProjectEntity getProjectByiD(int id) ;
public void editProject(ProjectEntity projectEntity) ;
public List<ProjectEntity>  swapProj(int id, List<ProjectEntity> list) ;
public List<StatusEntity>  swapStat(int id, List<StatusEntity> list) ;
}
