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

public class UserRepository {

	public int deleteUserById(int id) {
		int rowCount=0;
		String sqlString = "Delete FROM users u where u.id =?";
		
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
	
	public static void addUser(UserEntity userEntity) {
	    String sqlString = "INSERT INTO users (password, first_name, last_name, username, phone, id_role) VALUES (?, ?, ?, ?, ?, ?)";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	        connection = MysqlConfig.getConnection();
	        preparedStatement = connection.prepareStatement(sqlString);

	        preparedStatement.setString(1, userEntity.getPasString());
	        preparedStatement.setString(2, userEntity.getfName());
	        preparedStatement.setString(3, userEntity.getlName());
	        preparedStatement.setString(4, userEntity.getuName());
	        preparedStatement.setString(5, userEntity.getPhoneString());
	        preparedStatement.setInt(6, userEntity.getRoleEntity().getId());

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
	
	
	public List<UserEntity> getAllUser() {
		   List<UserEntity> listUserEntities = new ArrayList<UserEntity>();
		   
		   String sql = "SELECT  u.id ,u.first_name ,u.last_name ,u.username,r.name  "
		   		+ "FROM users u join roles r "
		   		+ "on u.id_role  = r.id ";
		   
		   try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setfName(resultSet.getString("first_name"));
				userEntity.setlName(resultSet.getString("last_name"));
				userEntity.setuName(resultSet.getString("username"));
				
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setNameString(resultSet.getString("name"));
				
				userEntity.setRoleEntity(roleEntity);
				
				listUserEntities.add(userEntity);
			}
			
		} catch (SQLException e) {
			System.out.println("Error Catch: "+e.getMessage());
		}
		   return listUserEntities;
		
	}
	
}
