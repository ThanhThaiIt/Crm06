package crm06.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm06.config.MysqlConfig;
import crm06.entity.RoleEntity;

public class RoleRepository {
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
