package crm06.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm06.entity.ProjectEntity;
import crm06.entity.TaskEntity;
import crm06.service.ProjectService;
import crm06.service.ProjectServiceImp;
import crm06.service.TaskService;
import crm06.service.TaskServiceImp;
import crm06.service.UserService;
import crm06.service.UserServiceimpp;

@WebServlet(name = "taskController", urlPatterns = { "/add_task", "/task", "/edit_task" })
public class TaskController extends HttpServlet{
	TaskService taskService = new TaskServiceImp();
	UserService userService = new UserServiceimpp();
	ProjectService projectService = new ProjectServiceImp();
	
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String pathCall = req.getServletPath();
	switch (pathCall) {
	case "/add_task":
		moveTaskAdd(req, resp);
		break;

	case "/task":
		getTaskListAndMovingToTaskJSp(req, resp);
		break;
	case "/edit_task":
		int id = Integer.parseInt(req.getParameter("id"));
		getTaskByIdEdit(req, resp, id);

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
		action1(req, resp);
		break;
	case 2:
		action2(req, resp);
		break;

	default:
		break;
	}
	}

//private void action2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	int idTask = Integer.parseInt(req.getParameter("idT"));
//	int idProj = Integer.parseInt(req.getParameter("proj"));
//	String nameTask = req.getParameter("name");
//	int idUser = Integer.parseInt(req.getParameter("user"));
//	
//	String pStartDate = formatDateString(req.getParameter("pStDa"));
//    String pEndDate = formatDateString(req.getParameter("pEnDa"));
//	int idStatus = Integer.parseInt(req.getParameter("status"));
//
//    // Định dạng để parse ngày tháng
//    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//    try {
//        
//        Date startDateParsed = inputDateFormat.parse(pStartDate);
//        Date endDateParsed = inputDateFormat.parse(pEndDate);
//
//        
//        Timestamp startDate = new Timestamp(startDateParsed.getTime());
//        Timestamp endDate = new Timestamp(endDateParsed.getTime());
//        TaskEntity taskEntity = new TaskEntity(idTask, idUser, idProj, idStatus, nameTask, startDate, endDate);
//        taskService.editTask(taskEntity);
//       getTaskListAndMovingToTaskJSp(req, resp);
//       
//
//    } catch (ParseException e) {
//        e.printStackTrace();
//    }
//	
//}

private void action2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int idTask = Integer.parseInt(req.getParameter("idT"));
    int idProj = Integer.parseInt(req.getParameter("proj"));
    String nameTask = req.getParameter("name");
    int idUser = Integer.parseInt(req.getParameter("user"));
    
//    String pStartDate = formatDateString(req.getParameter("startDate"));
//    String pEndDate = formatDateString(req.getParameter("endDate"));
    String pStartDate = req.getParameter("startDate");
    String pEndDate = req.getParameter("endDate");
    int idStatus = Integer.parseInt(req.getParameter("status"));

    // Định dạng để parse ngày tháng
    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    try {
        // Validate "start date" va "end date" truoc khi parse
        if (pStartDate.isEmpty() || pEndDate.isEmpty()) {
            throw new IllegalArgumentException("Start date and end date cannot be empty.");
        }
        
        Date startDateParsed = inputDateFormat.parse(pStartDate);
        Date endDateParsed = inputDateFormat.parse(pEndDate);

        Timestamp startDate = new Timestamp(startDateParsed.getTime());
        Timestamp endDate = new Timestamp(endDateParsed.getTime());
        
        TaskEntity taskEntity = new TaskEntity(idTask, idUser, idProj, idStatus, nameTask, startDate, endDate);
        taskService.editTask(taskEntity);
        getTaskListAndMovingToTaskJSp(req, resp);

    } catch (ParseException e) {
        e.printStackTrace();
        // Handle parse exception (e.g., log it, show an error message to the user)
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
        // Handle illegal argument exception (e.g., log it, show an error message to the user)
    }
}
public static String formatDateString(String dateTimeString) {
    
    if (dateTimeString != null && dateTimeString.length() >= 10) {
        return dateTimeString.substring(0, 10);
    } else {
         
        return "";
    }
}



private void action1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int idProj = Integer.parseInt(req.getParameter("proj"));
	String nameTask = req.getParameter("name");
	int idUser = Integer.parseInt(req.getParameter("user"));
	
	Timestamp starTimestamp = formatTimeStamp(req.getParameter("startDate"));
	Timestamp endTimestamp = formatTimeStamp(req.getParameter("endDate"));
	int idStatus = Integer.parseInt(req.getParameter("status"));
	
	TaskEntity taskEntity = new TaskEntity( idUser, idProj, idStatus, nameTask, starTimestamp, endTimestamp);
	taskService.addTask(taskEntity);
	getTaskListAndMovingToTaskJSp(req, resp);
}

public Timestamp formatTimeStamp(String datee) {
	SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Timestamp datestam = null;
    try {
        // Parse các chuỗi nhập vào thành Date object
        Date startDateParsed = inputDateFormat.parse(datee);
        

        // Chuyển đổi Date object thành Timestamp
        datestam = new Timestamp(startDateParsed.getTime());
        

        

    } catch (ParseException e) {
        e.printStackTrace();
    }
    
    return datestam;
}



private void getTaskByIdEdit(HttpServletRequest req, HttpServletResponse resp, int id) throws ServletException, IOException {
	
	TaskEntity taskEntitie = taskService.getTaskByID(id);
	int idProject = taskEntitie.getProjectEntity().getId();
	int idUser = taskEntitie.getUserEntity().getId();
	int idStatus = taskEntitie.getStatusEntity().getId();
	
	//userService.swapUser(idUser, taskService.getAllListUser());
	
	
	
	
	
	req.setAttribute("task", taskService.getTaskByID(id));
	req.setAttribute("ListStatus", projectService.swapStat(idStatus, taskService.getAllListStatus())      );  
	req.setAttribute("ListUsers", userService.swapUser(idUser, taskService.getAllListUser()));
	req.setAttribute("ListProjects", projectService.swapProj(idProject, taskService.getAllListProject())         );
	req.getRequestDispatcher("task-edit.jsp").forward(req, resp);
	
}

private void getTaskListAndMovingToTaskJSp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	req.setAttribute("ListTasks", taskService.getAllTask());
	req.getRequestDispatcher("task.jsp").forward(req, resp);
}

private void moveTaskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setAttribute("ListStatus", taskService.getAllListStatus());
	req.setAttribute("ListUsers", taskService.getAllListUser());
	req.setAttribute("ListProjects", taskService.getAllListProject());
	req.getRequestDispatcher("task-add.jsp").forward(req, resp);
}


}
