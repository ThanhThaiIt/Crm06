package crm06.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm06.config.MysqlConfig;
import crm06.entity.RoleEntity;
import crm06.entity.StatisticalTaskEntity;
import crm06.entity.StatusEntity;
import crm06.entity.TaskEntity;
import crm06.entity.TaskEntityLists;
import crm06.entity.UserEntity;

public class TaskRepository {
	
	
	
	
	public  int countRows() {
	    int count = 0;
	    String sql = "SELECT COUNT(*) FROM task ";
	    
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
	
	
	public  List<TaskEntity> DisplayAllTaskLimit(int start, int count) {
	    List<TaskEntity> listTaskEntities = new ArrayList<>();

	    String sql = "SELECT\n" + "	t.id ,\n" + "	t.id_user ,\n" + "	t.id_project ,\n" + "	t.id_status ,\n"
				+ "	t.name ,\n" + "	t.start_date ,\n" + "	t.end_date,\n" + "	u.last_name as lastNameU,\n"
				+ "	s.name as stsName,\n" + "	p.name as proName\n" + "from\n" + "	task t\n" + "join users u on\n"
				+ "	t.id_user = u.id\n" + "JOIN status s on\n" + "	t.id_status = s.id\n" + "JOIN project p on\n"
				+ "	t.id_project = p.id LIMIT ?, ?";

	    try (Connection connection = MysqlConfig.getConnection();
	         PreparedStatement ptmt = connection.prepareStatement(sql)) {
	         
	        ptmt.setInt(1, start);
	        ptmt.setInt(2, count);

	        try (ResultSet resultSet = ptmt.executeQuery()) {
	            while (resultSet.next()) {
	            	TaskEntity taskEntity = new TaskEntity(resultSet.getInt("id"), resultSet.getString("lastNameU"),
							resultSet.getString("proName"), resultSet.getString("stsName"), resultSet.getString("name"),
							resultSet.getTimestamp("start_date"), resultSet.getTimestamp("end_date"));
					listTaskEntities.add(taskEntity);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Ensure that SQL exceptions are properly printed out
	    }

	    return listTaskEntities;
	}
	
	
	
	public TaskEntityLists getTasksByStatus(List<TaskEntity> taskList) {
        List<TaskEntity> chuaThucHienTasks = new ArrayList<>();
        List<TaskEntity> dangThucHienTasks = new ArrayList<>();
        List<TaskEntity> daThucHienTasks = new ArrayList<>();

        for (TaskEntity task : taskList) {
            switch (task.getStatusEntity().getId()) {
                case 1:
                    chuaThucHienTasks.add(task);
                    break;
                case 2:
                    dangThucHienTasks.add(task);
                    break;
                case 3:
                    daThucHienTasks.add(task);
                    break;
            }
        }

        return new TaskEntityLists(chuaThucHienTasks, dangThucHienTasks, daThucHienTasks);
    }

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

	public StatisticalTaskEntity getStatisticalTaskEntity(List<TaskEntity> taskList) {
		int chuaThucHien = 0;
		int dangThucHien = 0;
		int daThucHien = 0;

		for (TaskEntity task : taskList) {
			int status = task.getStatusEntity().getId();
			switch (status) {
			case 1:
				chuaThucHien++;
				break;
			case 2:
				dangThucHien++;
				break;
			case 3:
				daThucHien++;
				break;
			}
		}
		if (chuaThucHien == 0 && dangThucHien == 0 && dangThucHien == 0) {
			return new StatisticalTaskEntity(0, 0, 0);
		}

		int totalTasks = taskList.size();
//        double chuaThucHienPercent = (chuaThucHien / (double) totalTasks) * 100;
//        double dangThucHienPercent = (dangThucHien / (double) totalTasks) * 100;
//        double daThucHienPercent = (daThucHien / (double) totalTasks) * 100;
		double chuaThucHienPercent = round((chuaThucHien / (double) totalTasks) * 100, 2);
		double dangThucHienPercent = round((dangThucHien / (double) totalTasks) * 100, 2);
		double daThucHienPercent = round((daThucHien / (double) totalTasks) * 100, 2);

		return new StatisticalTaskEntity(chuaThucHienPercent, dangThucHienPercent, daThucHienPercent);
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

//	
//	public List<TaskEntity> getListTaskByIdUserForDetail(int id) {
//		TaskEntity taskEntity = null;
//		List<TaskEntity> lTaskEntities = new ArrayList<TaskEntity>();
//		    String sqlString = "SELECT\n"
//		    		+ "	t.id ,\n"
//		    		+ "	t.id_user as idu,\n"
//		    		+ "	t.id_project as idp,\n"
//		    		+ "	t.id_status as ids,\n"
//		    		+ "	t.name ,\n"
//		    		+ "	t.start_date ,\n"
//		    		+ "	t.end_date,\n"
//		    		+ "	u.last_name as lastNameU,\n"
//		    		+ "	s.name as stsName,\n"
//		    		+ "	p.name as proName\n"
//		    		+ "from\n"
//		    		+ "	task t\n"
//		    		+ "join users u on\n"
//		    		+ "	t.id_user = u.id\n"
//		    		+ "JOIN status s on\n"
//		    		+ "	t.id_status = s.id\n"
//		    		+ "JOIN project p on\n"
//		    		+ "	t.id_project = p.id WHERE u.id =?";
//
//		    try {
//		        Connection connection = MysqlConfig.getConnection();
//		        PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
//		        preparedStatement.setInt(1, id);
//
//		        // Thực hiện truy vấn và nhận kết quả trả về
//		        ResultSet resultSet = preparedStatement.executeQuery();
//		        
//		        // Nếu có kết quả, tạo đối tượng User từ dữ liệu kết quả
//		        if (resultSet.next()) {
//		        	taskEntity = new TaskEntity(resultSet.getInt("id"), resultSet.getInt("idu"), resultSet.getInt("idp"), 
//							resultSet.getInt("ids"), resultSet.getString("name"), resultSet.getTimestamp("start_date"), resultSet.getTimestamp("end_date"));
//		        
//		        	lTaskEntities.add(taskEntity);
//		        }
//		    } catch (SQLException e) {
//		        System.out.println("error: " + e.getMessage());
//		    }
//
//		    return lTaskEntities;
//	}
	
	
	public  StatisticalTaskEntity demSoLuongTaskTask(List<TaskEntity> taskList) {
		int chuaThucHien = 0;
		int dangThucHien = 0;
		int daThucHien = 0;
		for (TaskEntity task : taskList) {
			int status = task.getStatusEntity().getId();
			switch (status) {
			case 1:
				chuaThucHien++;
				break;
			case 2:
				dangThucHien++;
				break;
			case 3:
				daThucHien++;
				break;
			}
		}
		return new StatisticalTaskEntity(chuaThucHien, dangThucHien, daThucHien);
	}

	
	
	
	
	public List<TaskEntity> getAllListTask() {
	    List<TaskEntity> lTaskEntities = new ArrayList<>();
	    String sqlString = "SELECT\n" +
	            "    t.id,\n" +
	            "    t.id_user as idu,\n" +
	            "    t.id_project as idp,\n" +
	            "    t.id_status as ids,\n" +
	            "    t.name,\n" +
	            "    t.start_date,\n" +
	            "    t.end_date,\n" +
	            "    u.last_name as lastNameU,\n" +
	            "    s.name as stsName,\n" +
	            "    p.name as proName\n" +
	            "FROM\n" +
	            "    task t\n" +
	            "JOIN users u ON\n" +
	            "    t.id_user = u.id\n" +
	            "JOIN status s ON\n" +
	            "    t.id_status = s.id\n" +
	            "JOIN project p ON\n" +
	            "    t.id_project = p.id ";

	    try (Connection connection = MysqlConfig.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
	        // Thực hiện truy vấn và nhận kết quả trả về
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            // Duyệt qua tất cả các kết quả
	            while (resultSet.next()) {
	                TaskEntity taskEntity = new TaskEntity(
	                        resultSet.getInt("idu"),
	                        resultSet.getInt("idp"),
	                        resultSet.getInt("ids"),
	                        resultSet.getString("name"),
	                        resultSet.getTimestamp("start_date"),
	                        resultSet.getTimestamp("end_date"),
	                        resultSet.getString("lastNameU")
	                );
	                lTaskEntities.add(taskEntity);
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Error: " + e.getMessage());
	    }

	    return lTaskEntities;
	}

	
	
	
	
	
	public List<TaskEntity> getListTaskByIdProjectForDetail(int id) {
		List<TaskEntity> lTaskEntities = new ArrayList<>();
		String sqlString = "SELECT\n" + "    t.id,\n" + "    t.id_user as idu,\n" + "    t.id_project as idp,\n"
				+ "    t.id_status as ids,\n" + "    t.name,\n" + "    t.start_date,\n" + "    t.end_date,\n"
				+ "    u.last_name as lastNameU,\n" + "    s.name as stsName,\n" + "    p.name as proName\n" + "FROM\n"
				+ "    task t\n" + "JOIN users u ON\n" + "    t.id_user = u.id\n" + "JOIN status s ON\n"
				+ "    t.id_status = s.id\n" + "JOIN project p ON\n" + "    t.id_project = p.id WHERE p.id = ?";

		try (Connection connection = MysqlConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
			preparedStatement.setInt(1, id);

			// Thực hiện truy vấn và nhận kết quả trả về
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Duyệt qua tất cả các kết quả
				while (resultSet.next()) {
					TaskEntity taskEntity = new TaskEntity( resultSet.getInt("idu"),
							resultSet.getInt("idp"), resultSet.getInt("ids"), resultSet.getString("name"),
							resultSet.getTimestamp("start_date"), resultSet.getTimestamp("end_date"),resultSet.getString("lastNameU"));
					lTaskEntities.add(taskEntity);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return lTaskEntities;
	}
	
	
	public List<TaskEntity> getListTaskByIdUserForDetail(int id) {
		List<TaskEntity> lTaskEntities = new ArrayList<>();
		String sqlString = "SELECT\n" + "    t.id,\n" + "    t.id_user as idu,\n" + "    t.id_project as idp,\n"
				+ "    t.id_status as ids,\n" + "    t.name,\n" + "    t.start_date,\n" + "    t.end_date,\n"
				+ "    u.last_name as lastNameU,\n" + "    s.name as stsName,\n" + "    p.name as proName\n" + "FROM\n"
				+ "    task t\n" + "JOIN users u ON\n" + "    t.id_user = u.id\n" + "JOIN status s ON\n"
				+ "    t.id_status = s.id\n" + "JOIN project p ON\n" + "    t.id_project = p.id WHERE u.id = ?";

		try (Connection connection = MysqlConfig.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlString)) {
			preparedStatement.setInt(1, id);

			// Thực hiện truy vấn và nhận kết quả trả về
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				// Duyệt qua tất cả các kết quả
				while (resultSet.next()) {
					TaskEntity taskEntity = new TaskEntity(resultSet.getInt("id"), resultSet.getInt("idu"),
							resultSet.getInt("idp"), resultSet.getInt("ids"), resultSet.getString("name"),
							resultSet.getTimestamp("start_date"), resultSet.getTimestamp("end_date"));
					lTaskEntities.add(taskEntity);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		return lTaskEntities;
	}

	public TaskEntity getTaskById(int id) {
		TaskEntity taskEntity = null;
		String sqlString = "SELECT\n" + "	t.id ,\n" + "	t.id_user as idu,\n" + "	t.id_project as idp,\n"
				+ "	t.id_status as ids,\n" + "	t.name ,\n" + "	t.start_date ,\n" + "	t.end_date,\n"
				+ "	u.last_name as lastNameU,\n" + "	s.name as stsName,\n" + "	p.name as proName\n" + "from\n"
				+ "	task t\n" + "join users u on\n" + "	t.id_user = u.id\n" + "JOIN status s on\n"
				+ "	t.id_status = s.id\n" + "JOIN project p on\n" + "	t.id_project = p.id WHERE t.id =?";

		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setInt(1, id);

			// Thực hiện truy vấn và nhận kết quả trả về
			ResultSet resultSet = preparedStatement.executeQuery();

			// Nếu có kết quả, tạo đối tượng User từ dữ liệu kết quả
			if (resultSet.next()) {
				taskEntity = new TaskEntity(resultSet.getInt("id"), resultSet.getInt("idu"), resultSet.getInt("idp"),
						resultSet.getInt("ids"), resultSet.getString("name"), resultSet.getTimestamp("start_date"),
						resultSet.getTimestamp("end_date"));
			}
		} catch (SQLException e) {
			System.out.println("error: " + e.getMessage());
		}

		return taskEntity;
	}

	public int deleteTaskById(int id) {
		int rowCount = 0;
		String sqlString = "Delete FROM task t where t.id =?";

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

	public List<TaskEntity> getAllTask() {
		List<TaskEntity> listTaskEntities = new ArrayList<TaskEntity>();

		String sql = "SELECT\n" + "	t.id ,\n" + "	t.id_user ,\n" + "	t.id_project ,\n" + "	t.id_status ,\n"
				+ "	t.name ,\n" + "	t.start_date ,\n" + "	t.end_date,\n" + "	u.last_name as lastNameU,\n"
				+ "	s.name as stsName,\n" + "	p.name as proName\n" + "from\n" + "	task t\n" + "join users u on\n"
				+ "	t.id_user = u.id\n" + "JOIN status s on\n" + "	t.id_status = s.id\n" + "JOIN project p on\n"
				+ "	t.id_project = p.id ;";

		try {
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TaskEntity taskEntity = new TaskEntity(resultSet.getInt("id"), resultSet.getString("lastNameU"),
						resultSet.getString("proName"), resultSet.getString("stsName"), resultSet.getString("name"),
						resultSet.getTimestamp("start_date"), resultSet.getTimestamp("end_date"));
				listTaskEntities.add(taskEntity);
			}

		} catch (SQLException e) {
			System.out.println("Error Catch: " + e.getMessage());
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
