package crm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm06.config.MysqlConfig;
import crm06.entity.ProjectEntity;
import crm06.entity.RoleEntity;
import crm06.entity.StatusEntity;
import crm06.entity.UserEntity;

public class ProjectRepository {
	public  List<StatusEntity> swapStatusToFirst(int id, List<StatusEntity> lStatusEntities) {
        // Find the index of the user with the given id
        int index = -1;
        for (int i = 0; i < lStatusEntities.size(); i++) {
            if (lStatusEntities.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        // If user with the given id is found, swap with the first element
        if (index != -1) {
        	StatusEntity user = lStatusEntities.get(index);
            lStatusEntities.remove(index);
            lStatusEntities.add(0, user);
        }

        // Return the modified list
        return lStatusEntities;
    }
	public  List<ProjectEntity> swapProjectToFirst(int id, List<ProjectEntity> projectEntities) {
        // Find the index of the user with the given id
        int index = -1;
        for (int i = 0; i < projectEntities.size(); i++) {
            if (projectEntities.get(i).getId() == id) {
                index = i;
                break;
            }
        }

        // If user with the given id is found, swap with the first element
        if (index != -1) {
        	ProjectEntity user = projectEntities.get(index);
            projectEntities.remove(index);
            projectEntities.add(0, user);
        }

        // Return the modified list
        return projectEntities;
    }
	public int editRoleById(ProjectEntity projectEntity) {
	    int rowCount = 0;
	    String sqlString = "UPDATE project SET name = ?,start_date = ?, end_date = ? WHERE id = ?";
	    
	    try {
	        Connection connection = MysqlConfig.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
	        
	        // Set the parameters for the update query
	        
	        preparedStatement.setString(1, projectEntity.getName());
	        preparedStatement.setTimestamp(2, projectEntity.getStart_date());
		       preparedStatement.setTimestamp(3, projectEntity.getEnd_date());
		       preparedStatement.setInt(4, projectEntity.getId());
	        // Execute update and get the number of affected rows
	        rowCount = preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("error: " + e.getMessage());
	    }
	    
	    return rowCount;
	}
	
	public ProjectEntity getProjectById(int id) {
		ProjectEntity projectEntity = null;
		    String sqlString = "SELECT * FROM project WHERE id = ?";

		    try {
		        Connection connection = MysqlConfig.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
		        preparedStatement.setInt(1, id);

		        // Thực hiện truy vấn và nhận kết quả trả về
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        // Nếu có kết quả, tạo đối tượng User từ dữ liệu kết quả
		        if (resultSet.next()) {
		        	 projectEntity = new ProjectEntity();
		            projectEntity.setId(resultSet.getInt("id"));
		            projectEntity.setName(resultSet.getString("name"));
		            projectEntity.setStart_date(resultSet.getTimestamp("start_date"));
		            projectEntity.setEnd_date(resultSet.getTimestamp("end_date"));
		            
		            		
		            
		        }
		    } catch (SQLException e) {
		        System.out.println("error: " + e.getMessage());
		    }

		    return projectEntity;
	}
	public int deleteProjectById(int id) {
		int rowCount=0;
		String sqlString = "Delete FROM project p where p.id =?";
		
		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, id);
			
			
			// excuteupdate trả về int số hàng đã thực hiện
			rowCount =preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error: "+e.getMessage());
		}
		
		return rowCount;
		
	}
	
	public static void addProject(ProjectEntity projectEntity) {
	    String sqlString = "INSERT INTO project (name, start_date, end_date) VALUES (?, ?, ?)";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = MysqlConfig.getConnection();
	        preparedStatement = connection.prepareStatement(sqlString);

	        preparedStatement.setString(1, projectEntity.getName());
	       preparedStatement.setTimestamp(2, projectEntity.getStart_date());
	       preparedStatement.setTimestamp(3, projectEntity.getEnd_date());

	       
	       // return về int
	        int rowsAffected = preparedStatement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Insert successful.");
	        } else {
	            System.out.println("Insert failed.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public static List<ProjectEntity> getAllProject() {
	    String sqlString = "SELECT * FROM project";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<ProjectEntity> projectEntities = new ArrayList<>();

	    try {
	        connection = MysqlConfig.getConnection();
	        preparedStatement = connection.prepareStatement(sqlString);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            ProjectEntity projectEntity = new ProjectEntity();
	            projectEntity.setId(resultSet.getInt("id"));
	            projectEntity.setName(resultSet.getString("name"));
	            projectEntity.setStart_date(resultSet.getTimestamp("start_date"));
	            projectEntity.setEnd_date(resultSet.getTimestamp("end_date"));
	            projectEntities.add(projectEntity);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return projectEntities;
	}
}
