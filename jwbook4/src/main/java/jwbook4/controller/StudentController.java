package jwbook4.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwbook4.dao.StudentDAO;

@WebServlet("/studentControl")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //Controller에서 사용할 dao객체 선언
	StudentDAO dao;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//StudentDAO객체 주입(injection)
		dao = new StudentDAO();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 명령어 얻기 
		String action = request.getParameter("action");
		String view="";
		if(request.getParameter("action") == null) {
			getServletContext()
			.getRequestDispatcher("/studentControl?action=list")
			.forward(request, response);
		}else {
		  switch(action){
		  case "list": view = list(request,response); break;
		  case "insert": view = insert(request,response); break;
		}
		  getServletContext()
			.getRequestDispatcher("/ch09/"+view) // /ch09/studentInfo.jsp
			.forward(request, response); 
		}
	}

	private String insert(HttpServletRequest request, HttpServletResponse response) {
		return "";
	}
	private String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("students", dao.getAll());
		return "studentInfo.jsp"; // /ch09/studentInfo.jsp
	}
}
