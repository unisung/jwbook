package jwbook4.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import jwbook4.dao.StudentDAO;
import jwbook4.model.Student;

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
		//한글 문자셋 설정하기
 		request.setCharacterEncoding("UTF-8");
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
		  case "info": view = info(request,response); break;
		  case "update": view = update(request,response); break;
		  case "delete": view = delete(request,response); break;
		}
		  if(view.indexOf(':') == -1) {
		  getServletContext()
			.getRequestDispatcher("/ch09/"+view) // /ch09/studentInfo.jsp
			.forward(request, response);
		  } else {
			 //view = view.substring(view.indexOf(':')+1);
			 //System.out.println("view: "+ getServletContext().getContextPath()+view);
			 //view = getServletContext().getContextPath()+view;
			 view = view.substring(view.indexOf(':')+2);
			 response.sendRedirect(view);
		  }
		}
	}
	private String delete(HttpServletRequest request, HttpServletResponse response) {
		//삭제할 id 받기 
		String id = request.getParameter("id");
		//dao에서 삭제 처리
		 dao.delete(id);
		 //리스트로 리다이렉트
		 return "redirect:/studentControl";
	}

	//정보수정
    private String update(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String[]> map= request.getParameterMap();
		Set<String> keySet = map.keySet();
		Iterator<String> itor = keySet.iterator();
		while(itor.hasNext()) {
			String name = itor.next();
			System.out.println(name +"= "+ map.get(name)[0]);
		}
		//파라미터를 Student객체의 속성들에게 자동 setting처리
		Student s = new Student();
		try {
			BeanUtils.populate(s, request.getParameterMap());//한번에 여러개의 파라미터 받기
		} catch (Exception e) {
			e.printStackTrace();
		}
		//dao수정처리
		dao.update(s);
		return "redirect:/studentControl?action=info&id="+s.getId();
	}

	//해당id의 정보 출력
	private String info(HttpServletRequest request, HttpServletResponse response) {
            String id = request.getParameter("id");
            Student student = dao.getInfo(id);
            request.setAttribute("student", student);//전달할 객체 request에 저장
		return "personalInfo.jsp";//이동(forward)할 페이지
	}

	private String insert(HttpServletRequest request, HttpServletResponse response) {
		Student s = new Student();
		try {
			BeanUtils.populate(s, request.getParameterMap());//한번에 여러개의 파라미터 받기
		} catch (Exception e) {
			e.printStackTrace();
		}
		//DAO객체의 insert()메소드로 DB저장
		dao.insert(s);
		//return list(request, response); // sendRedirect();
		return "redirect:/studentControl";
	}
	private String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("students", dao.getAll());
		return "studentInfo.jsp"; // /ch09/studentInfo.jsp
	}
}
