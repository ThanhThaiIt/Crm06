package crm06.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm06.config.MysqlConfig;
import crm06.entity.UserEntity;
@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String emaiString = req.getParameter("email"); 
			String passwordd = 	req.getParameter("password");
			
			String queryString = "SELECT  * FROM users u WHERE u.username = '"+emaiString+"' AND u.password = '"+passwordd+"'";
			Connection connection = MysqlConfig.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			
			// excuteQuery: Giành cho lấy dữ liệu 
			// excuteUpdate: khác select
			// resultSet: Đại diện 
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
			List<UserEntity> listsUserEntities = new ArrayList<UserEntity>();
			
			
			while (resultSet.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setuName(resultSet.getString("username"));
				listsUserEntities.add(userEntity);
				
			}
			
			if (listsUserEntities.size()>0) {
				Cookie logined = new Cookie("logined", "true");
				resp.addCookie(logined);
				// lấy context(tên web)
				String contxt = req.getContextPath();
				
				resp.sendRedirect(contxt+"/user");
				System.out.println("Đăng nhập thành công");
			}else {
				System.out.println("Đằn nhập thất bai");
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi login");
		}
	}
}
