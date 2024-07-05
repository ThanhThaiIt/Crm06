package crm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm06.config.MysqlConfig;
import crm06.entity.RoleEntity;
import crm06.entity.StatusEntity;
import crm06.entity.TaskEntity;
import crm06.entity.UserEntity;

public class TaskRepository {
	
	
	public int editTaskById(TaskEntity taskEntity) {
	    int rowCount = 0;
	    String sqlString = "UPDATE task SET id_user = ?, id_project = ?, id_status = ?, name = ?, start_date = ?, end_date = ? WHERE id = ?";
	    
	    try {
	        Connection connection = MysqlConfig.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
	        
	        // Set the parameters for the update query
	        preparedStatement.setInt(1, taskEntity.getUserEntity().getId());
	        preparedStatement.setInt(2, taskEntity.getProjectEntity().getId());
	        preparedStatement.setInt(3, taskEntity.getStatusEntity().getId());
	        preparedStatement.setString(4, taskEntity.getName());
	        preparedStatement.setTimestamp(5, taskEntity.getStart_date());
	        preparedStatement.setTimestamp(6, taskEntity.getEnd_date());
	        preparedStatement.setInt(7, taskEntity.getId());
	        
	        // Execute update and get the number of affected rows
	        rowCount = preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("error: " + e.getMessage());
	    }
	    
	    return rowCount;
	}

	
	public TaskEntity getTaskById(int id) {
		TaskEntity taskEntity = null;
		    String sqlString = "SELECT\n"
		    		+ "	t.id ,\n"
		    		+ "	t.id_user as idu,\n"
		    		+ "	t.id_project as idp,\n"
		    		+ "	t.id_status as ids,\n"
		    		+ "	t.name ,\n"
		    		+ "	t.start_date ,\n"
		    		+ "	t.end_date,\n"
		    		+ "	u.last_name as lastNameU,\n"
		    		+ "	s.name as stsName,\n"
		    		+ "	p.name as proName\n"
		    		+ "from\n"
		    		+ "	task t\n"
		    		+ "join users u on\n"
		    		+ "	t.id_user = u.id\n"
		    		+ "JOIN status s on\n"
		    		+ "	t.id_status = s.id\n"
		    		+ "JOIN project p on\n"
		    		+ "	t.id_project = p.id WHERE t.id =?";

		    try {
		        Connection connection = MysqlConfig.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
		        preparedStatement.setInt(1, id);

		        // Thực hiện truy vấn và nhận kết quả trả về
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        // Nếu có kết quả, tạo đối tượng User từ dữ liệu kết quả
		        if (resultSet.next()) {
		        	taskEntity = new TaskEntity(resultSet.getInt("id"), resultSet.getInt("idu"), resultSet.getInt("idp"), 
							resultSet.getInt("ids"), resultSet.getString("name"), resultSet.getTimestamp("start_date"), resultSet.getTimestamp("end_date"));
		        }
		    } catch (SQLException e) {
		        System.out.println("error: " + e.getMessage());
		    }

		    return taskEntity;
	}

	public int deleteTaskById(int id) {
		int rowCount=0;
		String sqlString = "Delete FROM task t where t.id =?";
		
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
	
	public List<TaskEntity> getAllTask() {
		   List<TaskEntity> listTaskEntities = new ArrayList<TaskEntity>();
		   
		   String sql = "SELECT\n"
		   		+ "	t.id ,\n"
		   		+ "	t.id_user ,\n"
		   		+ "	t.id_project ,\n"
		   		+ "	t.id_status ,\n"
		   		+ "	t.name ,\n"
		   		+ "	t.start_date ,\n"
		   		+ "	t.end_date,\n"
		   		+ "	u.last_name as lastNameU,\n"
		   		+ "	s.name as stsName,\n"
		   		+ "	p.name as proName\n"
		   		+ "from\n"
		   		+ "	task t\n"
		   		+ "join users u on\n"
		   		+ "	t.id_user = u.id\n"
		   		+ "JOIN status s on\n"
		   		+ "	t.id_status = s.id\n"
		   		+ "JOIN project p on\n"
		   		+ "	t.id_project = p.id ;";
		   
		   try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TaskEntity taskEntity = new TaskEntity(resultSet.getInt("id"), resultSet.getString("lastNameU"), resultSet.getString("proName"), 
						resultSet.getString("stsName"), resultSet.getString("name"), resultSet.getTimestamp("start_date"), resultSet.getTimestamp("end_date"));
				listTaskEntities.add(taskEntity);
			}
			
		} catch (SQLException e) {
			System.out.println("Error Catch: "+e.getMessage());
		}
		   return listTaskEntities;
		
	}
	
	
	public static void addTask(TaskEntity taskEntity) {
	    String sqlString = "INSERT INTO task (id_user, id_project, id_status, name, start_date,end_date) VALUES (?, ?, ?, ?, ?, ?)";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = MysqlConfig.getConnection();
	        preparedStatement = connection.prepareStatement(sqlString);

	        preparedStatement.setInt(1, taskEntity.getUserEntity().getId());
	        preparedStatement.setInt(2, taskEntity.getProjectEntity().getId());
	        preparedStatement.setInt(3, taskEntity.getStatusEntity().getId());
	        preparedStatement.setString(4, taskEntity.getName());
	        preparedStatement.setTimestamp(5, taskEntity.getStart_date());
	        preparedStatement.setTimestamp(6, taskEntity.getEnd_date());

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
	
	public static List<StatusEntity> getAllStattus() {
	    String sqlString = "SELECT * FROM status";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<StatusEntity> statusEntities = new ArrayList<>();

	    try {
	        connection = MysqlConfig.getConnection();
	        preparedStatement = connection.prepareStatement(sqlString);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            
	            
	            StatusEntity statusEntity = new StatusEntity(resultSet.getInt("id"), resultSet.getString("name"));
	            statusEntities.add(statusEntity);
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

	    return statusEntities;
	}
}
