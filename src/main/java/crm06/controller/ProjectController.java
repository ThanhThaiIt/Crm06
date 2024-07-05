package crm06.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm06.entity.ProjectEntity;
import crm06.service.ProjectService;
import crm06.service.ProjectServiceImp;
@WebServlet(name = "projectController", urlPatterns = { "/add_project", "/project", "/edit_project" })
public class ProjectController extends HttpServlet{
	
	ProjectService projectService = new ProjectServiceImp();

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String pathCall = req.getServletPath();
	switch (pathCall) {
	case "/add_project":
		moveProjectAdd(req, resp);
		break;

	case "/project":
		getProjectListAndMovingToProjectJSp(req, resp);
		break;
	case "/edit_project":
		int id = Integer.parseInt(req.getParameter("id"));
		getProjectByIdEdit(req, resp, id);

		break;

	default:
		throw new IllegalArgumentException("Unexpected Value: ");

	}
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
int typeAction = Integer.parseInt(req.getParameter("typee"));

switch (typeAction) {
case 1:
	actionProject1(req, resp);
	break;
case 2:
	actionProject2(req, resp);
	break;

default:
	break;
}
}


private void getProjectByIdEdit(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
	
	req.setAttribute("projectByID", projectService.getProjectByiD(id));
	req.getRequestDispatcher("groupWorkEdit.jsp").forward(req, resp);
}

private void actionProject2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int idProj = Integer.parseInt(req.getParameter("idP"));
    String pName = req.getParameter("pName");
    String pStartDate = formatDateString(req.getParameter("pStDa"));
    String pEndDate = formatDateString(req.getParameter("pEnDa"));

    // Định dạng để parse ngày tháng
    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try {
        
        Date startDateParsed = inputDateFormat.parse(pStartDate);
        Date endDateParsed = inputDateFormat.parse(pEndDate);

        
        Timestamp startDate = new Timestamp(startDateParsed.getTime());
        Timestamp endDate = new Timestamp(endDateParsed.getTime());

        // Tạo ProjectEntity với thời gian thực
        ProjectEntity projectEntity = new ProjectEntity(idProj, pName, startDate, endDate);
        projectService.editProject(projectEntity);
        getProjectListAndMovingToProjectJSp(req, resp);

    } catch (ParseException e) {
        e.printStackTrace();
    }
}

public static String formatDateString(String dateTimeString) {
    
    if (dateTimeString != null && dateTimeString.length() >= 10) {
        return dateTimeString.substring(0, 10);
    } else {
         
        return "";
    }
}
private void actionProject1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String pName = req.getParameter("pName");
    String pStartDate = req.getParameter("pStDa");
    String pEndDate = req.getParameter("pEnDa");

    // Định dạng để parse ngày tháng
    SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    try {
        // Parse các chuỗi nhập vào thành Date object
        Date startDateParsed = inputDateFormat.parse(pStartDate);
        Date endDateParsed = inputDateFormat.parse(pEndDate);

        // Chuyển đổi Date object thành Timestamp
        Timestamp startDate = new Timestamp(startDateParsed.getTime());
        Timestamp endDate = new Timestamp(endDateParsed.getTime());

        // Tạo ProjectEntity với thời gian thực
        ProjectEntity projectEntity = new ProjectEntity(pName, startDate, endDate);
        projectService.addProject(projectEntity);
        getProjectListAndMovingToProjectJSp(req, resp);

    } catch (ParseException e) {
        e.printStackTrace();
    }
}

private void getProjectListAndMovingToProjectJSp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setAttribute("projectList", projectService.getAllProject());
	req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
	
}
private void moveProjectAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
	
}

}
