package jwbook.ch05;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/regform")
public class MyRegForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청처리");
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print("<!DOCTYPE html><html><head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>회원 가입 폼 만들기 예제</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<h2>회원 가입</h2>\r\n"
				+ "<hr>\r\n"
				+ "<div id=\"regform\">\r\n"
				+ "	<form name=\"form1\" action=\"regProcess\">\r\n"
				+ "	 <label>이름</label><br>\r\n"
				+ "	 <input name=\"name\" size=\"40\"><br>\r\n"
				+ "	 <hr>\r\n"
				+ "	 <label>이메일</label><br>\r\n"
				+ "	 <input type=\"email\" name=\"email\" size=\"40\"><br>\r\n"
				+ "	 <button type=\"submit\">가입</button>\r\n"
				+ "	</form>\r\n"
				+ "</div>\r\n"
				+ "<div id=\"result\" class=\"result\">\r\n"
				+ "	<h3>가입정보</h3>\r\n"
				+ "	<hr>\r\n"
				+ "	이름:<span id=\"rname\"></span><br>\r\n"
				+ "	이메일:<span id=\"remail\"></span><br>\r\n"
				+ "</div>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}

	@Override
	public void init() throws ServletException {
			System.out.println("초기화 메소드");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
