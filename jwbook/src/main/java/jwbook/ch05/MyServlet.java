package jwbook.ch05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8080/jwbook/hello <-> c:/dev.myworkspace/jwbook/src/ch05/MyServlet.java
@WebServlet("/hello")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().print("hello");
		PrintWriter out = response.getWriter();
		out.print("<html><body>");
		out.print(request.getParameter("name")+"<br>");
		out.print(request.getParameterValues("name")+"<br>");
		String[] names = request.getParameterValues("name");
	    System.out.println(Arrays.toString(names));
		
		out.print(request.getRequestURL()+"<br>");
		out.print(request.getRequestURI()+"<br>");
		out.print(request.getScheme()+"<br>");
		out.print(request.getServerName()+"<br>");
		out.print(request.getServerPort()+"<br>");
		out.print(request.getContextPath()+"<br>");
		out.print(request.getMethod()+"<br>");
		out.print(request.isSecure()+"<br>");
		out.print(request.getLocale()+"<br>");
		out.print(request.getProtocol()+"<br>");
		out.print(request.getLocalAddr()+"<br>");
		out.print(request.getRemoteAddr()+"<br>");
		out.print("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
