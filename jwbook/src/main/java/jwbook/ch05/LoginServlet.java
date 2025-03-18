package jwbook.ch05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//get방식 요청에 대한 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//loginForm.jsp로 이동-get방식
		RequestDispatcher dispatcher = request.getRequestDispatcher("loginForm.jsp");
		dispatcher.forward(request, response);
	}
    //post방식 요청에 대한 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String user = request.getParameter("email");
		String passwd = request.getParameter("password");
		
	    response.setContentType("text/html; charset=utf-8");
	    response.getWriter().append("user:"+user+", passwd:"+passwd);
	    //로그인 처리
	    HttpSession session = request.getSession();
	    //세션에 로그인 정보 저장
	    session.setAttribute("user", user);
	    //로그 출력
	    System.out.println("세션정보:"+session.getAttribute("user"));
	}
}
