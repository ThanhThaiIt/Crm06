package crm06.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "roleFilter", urlPatterns = { "/role","/add_role", "/edit_role", "/login" })
public class RoleFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		boolean isExist = false;
		Cookie[] cookies = req.getCookies();
		
		
//		for (Cookie cookie : cookies) {
//			String nameCookie = cookie.getName();
//			if (nameCookie.equals("logined")) {
//				isExist = true;
//				break;
//			}
//		}
		
		if (cookies != null) { // Add null check here
	        for (Cookie cookie : cookies) {
	            String nameCookie = cookie.getName();
	            if (nameCookie.equals("logined")) {
	                isExist = true;
	                break; // No need to continue once the cookie is found
	            }
	        }
	    }
		
		
		
		String conetxt = req.getContextPath();
		// lấy linkservlet mà lience đnag gọi
		String path = req.getServletPath();
		boolean shouldContinue = true;
		switch (path) {
		case "/role":
			if (isExist) {
				chain.doFilter(req, resp);
			}else {
				System.out.println("hello LoginPage");
				resp.sendRedirect(conetxt+"/login");
				shouldContinue = false;
			}

			break;
			
		case "/login":
			if (isExist) {
				
				resp.sendRedirect(conetxt+"/role");
				shouldContinue = false;
			}else {
				chain.doFilter(req, resp);
			}
			break;
			
		case "/add_role":
			if (isExist) {
				chain.doFilter(req, resp);
			}else {
				System.out.println("hello LoginPage");
				resp.sendRedirect(conetxt+"/login");
				shouldContinue = false;
			}

			break;
		case "/edit_role":
			if (isExist) {
				chain.doFilter(req, resp);
			}else {
				System.out.println("hello LoginPage");
				resp.sendRedirect(conetxt+"/login");
				shouldContinue = false;
			}

			break;
			
			
		default:
			System.out.println("khong tim thay link");
			// break;
		}
		
	}

}
