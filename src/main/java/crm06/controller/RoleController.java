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

@WebServlet(name = "roleController", urlPatterns = { "/role","/add_role", "/edit_role" })
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
			
		case "/edit_role":
			int id = Integer.parseInt(req.getParameter("id"));
			getRoleByIdEdit(req, resp, id);

			break;

		default:
			throw new IllegalArgumentException("Unexpected Value: ");

		}

	}
	private void getRoleByIdEdit(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
		RoleEntity roleEntity = roleService.getRoleByID(id);
		req.setAttribute("roleByID", roleEntity);
		req.getRequestDispatcher("Role-edit.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int typeAction = Integer.parseInt(req.getParameter("typee"));

		switch (typeAction) {
		case 1:
			actionRole1(req, resp);
			break;
		case 2:
			actionRole2(req, resp);
			break;

		default:
			break;
		}
		
		
		
	}
	
	
	private void actionRole2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idRole = Integer.parseInt(req.getParameter("rId"));
		String nameRole = req.getParameter("rName");
		String desRole = req.getParameter("rDes");
		RoleEntity roleEntity = new RoleEntity(idRole, nameRole, desRole);
		roleService.editRole(roleEntity);
		getRoleListAndMovingToRoleJSp(req, resp);
	}
	private void actionRole1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nameRole = req.getParameter("roleName");
		String desRole = req.getParameter("roleDes");
		
		RoleEntity roleEntity = new RoleEntity(nameRole, desRole);
		roleService.addRole(roleEntity);
		getRoleListAndMovingToRoleJSp(req, resp);
	}
	
//	public void forwardRoleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		List<RoleEntity> list = userService.getAllRole();
//		req.setAttribute("ListRole", list);
//		req.getRequestDispatcher("role-table.jsp").forward(req, resp);
//	}
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
