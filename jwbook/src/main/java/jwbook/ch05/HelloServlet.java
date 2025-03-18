package jwbook.ch05;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//http://localhost:8081/jwbook/greet
//@WebServlet("/greet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        String p1 = request.getParameter("p1");
        String p2 = request.getParameter("p2");
        String sel = request.getParameter("sel");
        System.out.println("user="+user);
        System.out.println("p1="+p1);
        System.out.println("p2="+p2);
        System.out.println("sel="+sel);
        
		String message = "welcome to my Page!!";
        response.getWriter().append(message);
        
        //request객체에 "msg"라는 속성명으로 message저장 
        request.setAttribute("msg", message);
        request.setAttribute("user",user);
        request.setAttribute("p1",p1);
        request.setAttribute("p2",p2);
        request.setAttribute("sel",sel);
        
        
		//이동페이지로 이동할 객체 ReqeustDispatcher생성
		RequestDispatcher dispatcher 
            = request.getRequestDispatcher("greetResult.jsp");
        //이동처리
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
