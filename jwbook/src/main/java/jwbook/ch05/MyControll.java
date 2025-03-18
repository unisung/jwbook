package jwbook.ch05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//http:localhost:8080/jwbook/regProcess  <-> MyControll
@WebServlet("/regProcess")
public class MyControll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//현재 서블릿의 문자셋에 맞추기 request.setCharacterEncoding(문자셋)
		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		System.out.println("name="+name);
		System.out.println("email="+ email);
		
		//브라우저에 보낼때 현재 페이지의 문자셋 맞추기
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =  response.getWriter();
		out.print("name="+name);
		out.print("email="+ email);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
