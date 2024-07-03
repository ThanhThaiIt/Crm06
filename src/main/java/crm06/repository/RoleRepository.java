package crm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm06.config.MysqlConfig;
import crm06.entity.RoleEntity;
import crm06.entity.UserEntity;

public class RoleRepository {
	
	public int editRoleById(RoleEntity roleEntity) {
	    int rowCount = 0;
	    String sqlString = "UPDATE roles SET description = ?, name = ? WHERE id = ?";
	    
	    try {
	        Connection connection = MysqlConfig.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
	        
	        // Set the parameters for the update query
	        
	        preparedStatement.setString(1, roleEntity.getDescripString());
	        preparedStatement.setString(2, roleEntity.getNameString());
	        preparedStatement.setInt(3, roleEntity.getId());
	        
	        // Execute update and get the number of affected rows
	        rowCount = preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        System.out.println("error: " + e.getMessage());
	    }
	    
	    return rowCount;
	}
	
	public RoleEntity getRoleById(int id) {
		RoleEntity role = null;
		    String sqlString = "SELECT * FROM roles WHERE id = ?";

		    try {
		        Connection connection = MysqlConfig.getConnection();
		        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
		        preparedStatement.setInt(1, id);

		        // Thực hiện truy vấn và nhận kết quả trả về
		        ResultSet resultSet = preparedStatement.executeQuery();
		        
		        // Nếu có kết quả, tạo đối tượng User từ dữ liệu kết quả
		        if (resultSet.next()) {
		            int roleId = resultSet.getInt("id");
		            String descripttion = resultSet.getString("description");
		            String roleName = resultSet.getString("name");
		            
		            role = new RoleEntity(roleId, roleName, descripttion);
		            		
		            
		        }
		    } catch (SQLException e) {
		        System.out.println("error: " + e.getMessage());
		    }

		    return role;
	}
	
	public int deleteUserById(int id) {
		int rowCount=0;
		String sqlString = "Delete FROM roles r where r.id =?";
		
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
	
	
	
	public static List<RoleEntity> getAllRoles() {
	    String sqlString = "SELECT * FROM roles";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    List<RoleEntity> roleEntities = new ArrayList<>();

	    try {
	        connection = MysqlConfig.getConnection();
	        preparedStatement = connection.prepareStatement(sqlString);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            RoleEntity roleEntity = new RoleEntity();
	            roleEntity.setId(resultSet.getInt("id"));
	            roleEntity.setDescripString(resultSet.getString("description"));
	            roleEntity.setNameString(resultSet.getString("name"));
	            roleEntities.add(roleEntity);
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

	    return roleEntities;
	}
	public static void addRole(RoleEntity roleEntity) {
	    String sqlString = "insert into roles(description, name) values (?, ?)";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = MysqlConfig.getConnection();
	        preparedStatement = connection.prepareStatement(sqlString);

	        preparedStatement.setString(1, roleEntity.getDescripString());
	        preparedStatement.setString(2, roleEntity.getNameString());
	        
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
}
