package crm06.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm06.entity.StatisticalTaskEntity;
import crm06.service.ProjectService;
import crm06.service.ProjectServiceImp;

@WebServlet(name = "indexController", urlPatterns = { "/index" })
public class IndexCOntroller extends HttpServlet{
	ProjectService projectService = new ProjectServiceImp();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathCall = req.getServletPath();
		switch (pathCall) {
		case "/index":
			moveIndex(req, resp);
			break;

		

		default:
			throw new IllegalArgumentException("Unexpected Value: ");

		}
	}

	private void moveIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StatisticalTaskEntity taskEntity = projectService.demSoLuongTaskTask(projectService.getAllListTask());
		req.setAttribute("listTaskIndex",taskEntity );
		req.getRequestDispatcher("index.html").forward(req, resp);
		
	}

}
