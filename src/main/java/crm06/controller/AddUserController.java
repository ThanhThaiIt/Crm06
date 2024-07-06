package crm06.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import crm06.entity.RoleEntity;
import crm06.entity.StatisticalTaskEntity;
import crm06.entity.TaskEntity;
import crm06.entity.TaskEntityLists;
import crm06.entity.UserEntity;
import crm06.service.TaskService;
import crm06.service.TaskServiceImp;
import crm06.service.UserService;
import crm06.service.UserServiceimpp;

@WebServlet(name = "userController", urlPatterns = { "/add_user", "/user", "/edit_user","/detail_user","/pagin_user" })
public class AddUserController extends HttpServlet {
	private UserService userService = new UserServiceimpp();
	private TaskService taskService = new TaskServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String pathCall = req.getServletPath();
		switch (pathCall) {
		case "/add_user":
			moveUserAdd(req, resp);
			break;

		case "/user":
			
			//getUserListAndMovingToUserJSp(req, resp);
			String idUPagi = req.getParameter("pageeid");
            //System.out.println("pageeid: " + idUPagi);  // Add this line for debugging
            userPagination(req, resp, idUPagi);
            break;
		case "/edit_user":
			int id = Integer.parseInt(req.getParameter("id"));
			getUserByIdEdit(req, resp, id);

			break;
		case "/detail_user":
			int idU = Integer.parseInt(req.getParameter("idU"));
			getUserByIdDetail(req, resp, idU);

			break;
		

		default:
			throw new IllegalArgumentException("Unexpected Value: ");

		}

	}

	

	private void userPagination(HttpServletRequest request, HttpServletResponse response, String idUPagi) throws ServletException, IOException {
		//String idCat = request.getParameter("idcate");
		String pageidstr = idUPagi;
		//if (idCat.equals("0") ) {

			
			if (pageidstr != null) {
				int pageid = Integer.parseInt(pageidstr);
				int count = 4;

				if (pageid == 1) {
					pageid=0;
				} else {
					pageid = pageid - 1;
					pageid = pageid * count ;
				}

				List<UserEntity> allUser = userService.DisplayAllUserLimit(pageid, count);
				
				

				int sumrow = userService.countRows();
				int maxpageid = 0;

				if ((sumrow / count) % 2 == 0) {
					maxpageid = (sumrow / count);
				} else {
					maxpageid = (sumrow / count) + 1;
				}

				request.setAttribute("maxpageid", maxpageid);

				request.setAttribute("usersLista", allUser);

				request.setAttribute("numberpage", Integer.parseInt(pageidstr));
				request.setAttribute("stt", 0);
				RequestDispatcher rd = request.getRequestDispatcher("user-table.jsp");
				rd.forward(request, response);
				
				
			}
		
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int typeAction = Integer.parseInt(req.getParameter("typee"));

		switch (typeAction) {
		case 1:
			action1(req, resp);
			break;
		case 2:
			action2(req, resp);
			break;

		default:
			break;
		}

//		if (typeAction==1) {
//			action1(req, resp);
//		}else {
//			System.out.println("Khong thanh cong roi");
//		}

	}

	private void action2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("idU"));
		String fName = req.getParameter("fname");
		String lName = req.getParameter("lname");
		String uName = req.getParameter("uname");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String roleS = req.getParameter("role");
		int roleint = Integer.parseInt(roleS);

		UserEntity userEntity = new UserEntity(id,password, fName, lName, uName, phone, roleint);
		
		userService.editUser(userEntity);

		getUserListAndMovingToUserJSp(req, resp);
	}

	private void action1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fName = req.getParameter("fname");
		String lName = req.getParameter("lname");
		String uName = req.getParameter("uname");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String roleS = req.getParameter("role");
		int roleint = Integer.parseInt(roleS);

		UserEntity userEntity = new UserEntity(password, fName, lName, uName, phone, roleint);

		// UserDAO.addUser(userEntity);
		userService.addUser(userEntity);
		// GetUserList
		getUserListAndMovingToUserJSp(req, resp);
	}

	private void getUserListAndMovingToUserJSp(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("usersList", userService.getAllUserEntity());
		req.getRequestDispatcher("user-table.jsp").forward(req, resp);
	}
	
	private void getUserByIdDetail(HttpServletRequest req, HttpServletResponse resp, int idU) throws ServletException, IOException {
		UserEntity userEntity = userService.getUserById(idU);
		String fullNameString= fullName(userEntity.getfName(), userEntity.getlName());
		
		List<TaskEntity> taskEntities = userService.getListTaskByidUser(idU);
		StatisticalTaskEntity tStatisticalTaskEntity = userService.getStatisticalTaskEntity(taskEntities);
		
		
		TaskEntityLists taskEntityLists = userService.getTasksByStatus(taskEntities);
		
		req.setAttribute("ListTaskStatus", taskEntityLists);
		req.setAttribute("Statis", tStatisticalTaskEntity);
		req.setAttribute("fullName", fullNameString);
		req.setAttribute("userByID", userEntity);
		req.getRequestDispatcher("user-details.jsp").forward(req, resp);
		
	}
	public  String fullName(String fname, String lname) {
        return fname + " " + lname;
    }

	private void getUserByIdEdit(HttpServletRequest req, HttpServletResponse resp, int id)
			throws ServletException, IOException {
		UserEntity userEntity = userService.getUserById(id);
		req.setAttribute("userByID", userEntity);
		List<RoleEntity> list = userService.getAllRole();
		req.setAttribute("ListRole", list);
		req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
	}

	private void moveUserAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<RoleEntity> list = userService.getAllRole();
		req.setAttribute("ListRole", list);
		req.getRequestDispatcher("user-add.jsp").forward(req, resp);
	}

}

// TODO Auto-generated method stub

//// Khai Bao Cookie
//Cookie cookie = new Cookie("demo", "HelloCookie");
//Cookie cookie2 = new Cookie("demo2", "HelloCookie2");
//
//// Tao Cookie
//resp.addCookie(cookie);
//resp.addCookie(cookie2);