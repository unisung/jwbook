package jwbook.ch05;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/helloRedirect")
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String params = "user=hong&p1=0112&p2=1202&sel=11786";
		//localhost:8080/jwbook/greet로 이동을 요청(클라이언트에게)
		response.sendRedirect("/jwbook/greet"+"?"+params);
		//URL rewriting
		//"localhost:8080/jwbook/greet?user=hong&p1=0112&p2=1202&sel=11786"
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
