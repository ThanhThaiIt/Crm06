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
	
	
	public  int countRows() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM users ";
	    
	    try (Connection connection = MysqlConfig.getConnection();
	         PreparedStatement ptmt = connection.prepareStatement(sql);
	         ResultSet rs = ptmt.executeQuery()) {
	         
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return count;
	}
	
	
	public static List<UserEntity> DisplayAllUserLimit(int start, int count) {
	    List<UserEntity> listUser = new ArrayList<>();

	    String sql = "SELECT  u.id ,u.first_name ,u.last_name ,u.username,r.name  " + "FROM users u join roles r "+ "on u.id_role  = r.id LIMIT ?, ?";
//"SELECT  u.id ,u.first_name ,u.last_name ,u.username,r.name  " + "FROM users u join roles r "+ "on u.id_role  = r.id LIMIT ?, ?"
	    try (Connection connection = MysqlConfig.getConnection();
	         PreparedStatement ptmt = connection.prepareStatement(sql)) {
	         
	        ptmt.setInt(1, start);
	        ptmt.setInt(2, count);

	        try (ResultSet resultSet = ptmt.executeQuery()) {
	            while (resultSet.next()) {
	                UserEntity userEntity = new UserEntity();
	                userEntity.setId(resultSet.getInt("id"));
	                userEntity.setfName(resultSet.getString("first_name"));
	                userEntity.setlName(resultSet.getString("last_name"));
	                userEntity.setuName(resultSet.getString("username"));

	                RoleEntity roleEntity = new RoleEntity();
	                roleEntity.setNameString(resultSet.getString("name"));

	                userEntity.setRoleEntity(roleEntity);

	                listUser.add(userEntity);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Ensure that SQL exceptions are properly printed out
	    }

	    return listUser;
	}

//	public static List<UserEntity> DisplayAllUserLimit(int start, int count) {
//		
//		
//		
//		List<UserEntity> listUser = new ArrayList<UserEntity>();
//		
//		
//		try {
//			Connection connection =MysqlConfig.getConnection();
//			
//
//			String sql = "select * from users where limit " + (start) + ", " + count + "";
//			PreparedStatement ptmt = connection.prepareStatement(sql);
//
//			ResultSet resultSet = ptmt.executeQuery();
//
//			if (resultSet.isBeforeFirst()) {
//				while (resultSet.next()) {
//
//					UserEntity userEntity = new UserEntity();
//					userEntity.setId(resultSet.getInt("id"));
//					userEntity.setfName(resultSet.getString("first_name"));
//					userEntity.setlName(resultSet.getString("last_name"));
//					userEntity.setuName(resultSet.getString("username"));
//
//					RoleEntity roleEntity = new RoleEntity();
//					roleEntity.setNameString(resultSet.getString("name"));
//
//					userEntity.setRoleEntity(roleEntity);
//
//					listUser.add(userEntity);
//				}
//			}
//
//		} catch (SQLException e) {
//			e.getMessage();
//		}
//
//		return listUser;
//	}

	public List<UserEntity> swapUserToFirst(int id, List<UserEntity> listUsers) {
		// Find the index of the user with the given id
		int index = -1;
		for (int i = 0; i < listUsers.size(); i++) {
			if (listUsers.get(i).getId() == id) {
				index = i;
				break;
			}
		}

		// If user with the given id is found, swap with the first element
		if (index != -1) {
			UserEntity user = listUsers.get(index);
			listUsers.remove(index);
			listUsers.add(0, user);
		}

		// Return the modified list
		return listUsers;
	}

	public int editUserById(UserEntity userEntity) {
		int rowCount = 0;
		String sqlString = "UPDATE users SET password = ?, first_name = ?, last_name = ?, username = ?, phone = ?, id_role = ? WHERE id = ?";

		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);

			// Set the parameters for the update query
			preparedStatement.setString(1, userEntity.getPasString());
			preparedStatement.setString(2, userEntity.getfName());
			preparedStatement.setString(3, userEntity.getlName());
			preparedStatement.setString(4, userEntity.getuName());
			preparedStatement.setString(5, userEntity.getPhoneString());
			preparedStatement.setInt(6, userEntity.getRoleEntity().getId());
			preparedStatement.setInt(7, userEntity.getId());

			// Execute update and get the number of affected rows
			rowCount = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error: " + e.getMessage());
		}

		return rowCount;
	}

	public UserEntity getUserById(int id) {
		UserEntity user = null;
		String sqlString = "SELECT * FROM users WHERE id = ?";

		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, id);

			// Thực hiện truy vấn và nhận kết quả trả về
			ResultSet resultSet = preparedStatement.executeQuery();

			// Nếu có kết quả, tạo đối tượng User từ dữ liệu kết quả
			if (resultSet.next()) {
				int userId = resultSet.getInt("id");
				String passWord = resultSet.getString("password");
				String fname = resultSet.getString("first_name");
				String lname = resultSet.getString("last_name");
				String userName = resultSet.getString("username");
				String phone = resultSet.getString("phone");
				int idRole = resultSet.getInt("id_role");

				user = new UserEntity(userId, passWord, fname, lname, userName, phone, idRole);
			}
		} catch (SQLException e) {
			System.out.println("error: " + e.getMessage());
		}

		return user;
	}

	public int deleteUserById(int id) {
		int rowCount = 0;
		String sqlString = "Delete FROM users u where u.id =?";

		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, id);

			// excuteupdate trả về int số hàng đã thực hiện
			rowCount = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error: " + e.getMessage());
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

		String sql = "SELECT  u.id ,u.first_name ,u.last_name ,u.username,r.name  " + "FROM users u join roles r "
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
			System.out.println("Error Catch: " + e.getMessage());
		}
		return listUserEntities;

	}

}
