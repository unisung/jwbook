package jwbook.ch05;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\">\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<h2>Hello world!</h2><hr>"
				+" 현재 날짜와 시간은" + LocalDateTime.now()
				+ "</body>\r\n"
				+ "</html>");
	}

}
