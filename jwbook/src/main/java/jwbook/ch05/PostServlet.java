package jwbook.ch05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(description = "게시글리스트처리서블릿", urlPatterns = { "/mlist" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset=utf-8");
//		response.getWriter().append("게시글 리스트서블릿이에요~~~");
		
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("mlist.jsp");
		dispatcher.forward(request, response);
	}
}
