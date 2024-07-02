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
import crm06.service.RoleService;
import crm06.service.RoleServiceImp;

@WebServlet(name = "roleApiCOntroller", urlPatterns = { "/api/role" })
public class RoleApiController extends HttpServlet {

	private RoleService roleService = new RoleServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idRole = Integer.parseInt(req.getParameter("idRole"));

		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setStatusCode(200);
		baseResponse.setMessage("");
		baseResponse.setData(roleService.deleteRole(idRole));

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
