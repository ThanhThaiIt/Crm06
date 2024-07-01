package crm06.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import crm06.entity.RoleEntity;
import crm06.service.RoleService;
import crm06.service.RoleServiceImp;
import crm06.service.UserService;
import crm06.service.UserServiceimpp;

@WebServlet(name = "roleController", urlPatterns = { "/role","/add_role" })
public class RoleController extends HttpServlet {
	private RoleService roleService = new RoleServiceImp();
	private UserService userService = new UserServiceimpp();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		
		
		String pathCall = req.getServletPath();
		switch (pathCall) {
		case "/add_role":
			movingToAddRoleJsp(req, resp);
			break;

		case "/role":
			System.out.println("Đường ẫn đến rl");
			getRoleListAndMovingToRoleJSp(req, resp);
			break;

		default:
			throw new IllegalArgumentException("Unexpected Value: ");

		}

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nameRole = req.getParameter("roleName");
		String desRole = req.getParameter("roleDes");
		
		RoleEntity roleEntity = new RoleEntity(nameRole, desRole);
		roleService.addRole(roleEntity);
	}
	
	
	public void movingToAddRoleJsp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}
	public void getRoleListAndMovingToRoleJSp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		List<RoleEntity> listRole= roleService.getAllRole();
//		req.setAttribute("Roles", listRole);
//
//		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		List<RoleEntity> list = userService.getAllRole();
		req.setAttribute("ListRole", list);
		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		System.out.println("Đường ẫn đến rl2");
	}
}
