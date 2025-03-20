package jwbook.ch05;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwbook.ch05.model.Post;


@WebServlet("/write")
public class PostWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//writeForm.jsp로 이동처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("writeForm.html");
		dispatcher.forward(request, response);
	}

	//writeForm.jsp로 부터 넘어온 파라미터 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //파라미터로 넘어온 값 title, witer, content, email을 받아서 post추가하고
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String email = request.getParameter("email");
		
		Post post = new Post(title, content, LocalDateTime.now(), writer, email);
				
		// /mlist로 전달 localhost:8080/jwbook/mlist
		//response.sendRedirect("mlist");
		request.setAttribute("list", post.list);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("mlist.jsp");
		dispatcher.forward(request, response);
	}
}
