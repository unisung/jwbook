package jwbook.ch05;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookieServlet")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          Cookie cookie = new Cookie("user", "hong");
          Cookie cookie2 = new Cookie("name", "홍길동");
          Cookie cookie3 = new Cookie("tel", "010-1234-5678");
          response.addCookie(cookie);
          response.addCookie(cookie2);
          response.addCookie(cookie3);
          
  		 //브라우저에 보낼때 현재 페이지의 문자셋 맞추기
  		  response.setContentType("text/html; charset=UTF-8");
          response.getWriter().append("쿠키저장완료!!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
