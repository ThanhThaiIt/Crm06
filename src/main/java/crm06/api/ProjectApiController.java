package crm06.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm06.response.BaseResponse;
import crm06.service.ProjectService;
import crm06.service.ProjectServiceImp;
@WebServlet(name = "roleApiCOntroller", urlPatterns = { "/api/project" })
public class ProjectApiController extends HttpServlet{
	ProjectService projectService = new ProjectServiceImp();
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int idProject = Integer.parseInt(req.getParameter("idProj"));

	BaseResponse baseResponse = new BaseResponse();
	baseResponse.setStatusCode(200);
	baseResponse.setMessage("");
	baseResponse.setData(projectService.deleteProject(idProject));

	// tạo Gson để truyền baseResponse (Chuyển đối tượng về Gson)
	Gson gson = new Gson();
	String jsonDataString = gson.toJson(baseResponse);

	// chuyển về kiểu json
	resp.setContentType("application/json");
	PrintWriter printWriter = resp.getWriter();
	printWriter.append(jsonDataString);

	printWriter.close();
}

}
