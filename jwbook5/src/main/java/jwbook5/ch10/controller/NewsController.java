 package jwbook5.ch10.controller;

import java.io.IOException;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import jwbook5.ch10.dao.NewsDAO;
import jwbook5.ch10.model.News;

@WebServlet("/news.nhn")
@MultipartConfig(maxFileSize = 1014*1024*2, location = "C:/temp/img")
public class NewsController extends HttpServlet {
   private static final long serialVersionUID = 1L;
   //DB연동처리 객체 선언
   private NewsDAO dao;
   //서버애플리케이션 정보 객체 선언
   private ServletContext ctx;
   
   //웹 리소스 기본 경로 지정
   private final String START_PAGE = "ch10/newsList.jsp";
   public void init(ServletConfig config) throws ServletException {
      
      super.init( config); //설정 초기화
      dao = new NewsDAO(); //DAO객체 생성(DI-dependency Injection 의존성 주입)
      ctx = getServletContext(); //서블릿 정보 객체 생성(DI-dependency Injection 의존성 주입)
      
   
   }
      protected void service(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {
      
      //한글문자 깨짐방지
      //request.setCharacterEncoding("utf-8");
      String action = request.getParameter("action");
      
      dao = new NewsDAO();
      
      Method m;
      
      String view = null;
      if(action == null) {action = "listNews";}   //https://localhost:8080/jwbook5?action=listNews
      try {
         //
         m = this.getClass().getMethod(action, HttpServletRequest.class);
         //메소드 실행후 리턴값 받음
         view = (String)m.invoke(this, request);
         
      } catch (Exception e) {
         
         e.printStackTrace();
         ctx.log("요청 action 없음!!");
         request.setAttribute("error", "action 파라미터가 잘못 되었습니다!!!");
         view = START_PAGE; //초기페이지로 이동
      }
      
      //뷰 이동처리(주로 POST요청은 리다이렉트로)
      if(view.startsWith("redirect:/")) { //리다이렉트인 경우 - redirect:/listNews
         String rview = view.substring("redirect:/".length());
         response.sendRedirect(rview);
         
      }else { //forward인 경우
         RequestDispatcher dispatcher = request.getRequestDispatcher(view);
         
         dispatcher.forward(request, response);
         
      }
      }
   
   //뉴스리스트
   public String listNews(HttpServletRequest request) {
      List<News> list;
      try {
         list = dao.getAll();
         
         request.setAttribute("newslist", list);
      } catch (Exception e) {e.printStackTrace();
      ctx.log("뉴스 목록 생성 과정에서 문제 발생!!");
      request.setAttribute("error", "뉴스 목록이 정상적으로 처리되지 않았습니다.");
      }
      
      return "ch10/newsList.jsp";//forward할 페이지명 리턴
   }
   
   //뉴스 등록
   public String addNews(HttpServletRequest request) {
      News n = new News();
      try {
         //이미지 파일 저장
         Part part = request.getPart("file");
         
         String fileName = getFilename(part);
         
         
         //파일이 업로드완료되었으면 처리
         if(fileName != null && !fileName.isEmpty()) {
            part.write(fileName);
         }
         //입력값을 News 객체로 매핑
         BeanUtils.populate(n, request.getParameterMap()); //jsp 폼으로부터 전달된 파라미터를 News객체에 설정
         //이미지 파일 이름을 News 객체에도 저장
         n.setImg("/img/" + fileName);
         //DB에 저장 처리
         dao.addNews(n);
      } catch (Exception e) {
         e.printStackTrace();
         ctx.log("뉴스 추가 과정에서 문제 발생!!");
         request.setAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다.");
         return listNews(request); //오류시 목록 페이지로 이동 처리
      }
      return "redirect:/news.nhn?action=listNews";
   }
   
   //뉴스정보 출력
   public String getNews(HttpServletRequest request) {
	   //조회활 News번호 얻기
      int aid = Integer.parseInt(request.getParameter("aid"));
      try {
    	  //NewsDAO의 getNews(번호) 실행
         News news = dao.getNews(aid);
         //DB에서 조회한 News 정보를 request에 저장
         request.setAttribute("news", news);
         //로그로 출력
         ctx.log(news.toString());
      } catch (Exception e) {
        e.printStackTrace();
        ctx.log("뉴스 추가 과정에서 문제 발생!!!");
        request.setAttribute("error", "뉴스가 정상적으로 조회되지 않았습니다.");
        return listNews(request);  //오류발생시 다시 리스트 페이지로 이동처리
      }
      return "ch10/newsView.jsp";  //뉴스페이지로 이동
   }
   //뉴스 삭제
   public String deleteNews(HttpServletRequest request) {
     //파라미터로 넘어온 삭제할 뉴스번호 얻기
      int aid = Integer.parseInt(request.getParameter("aid"));
     try {
        //NewsDAO의 deleteNews()메소드 호출하여 DB에 삭제처리
         dao.deleteNews(aid);
   } catch (Exception e) {
      
      //오류메시지를 콘솔에 출력
      e.printStackTrace();
      //로그 출력
      ctx.log("뉴스 삭제 과정에서 문제 발생!!!");
      //리스트페이지에서 출력할 error 메세지 request에 저장
      request.setAttribute("error", "뉴스가 정상적으로 조회되지 않았습니다.");
      //리스트 페이지로 이동처리
      return listNews(request);
   }
      //리다이렉트로 list페이지로 이동처리
      return "redirect:/news.nhn?action=listNews";
   }
   //뉴스 수정form 이동
   public String updateform(HttpServletRequest request) {
		// 조회활 News번호 얻기
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			// NewsDAO의 getNews(번호) 실행
			News news = dao.getNews(aid);
			// DB에서 조회한 News 정보를 request에 저장
			request.setAttribute("news", news);
			// 로그로 출력
			ctx.log(news.toString());
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("뉴스 조회 과정에서 문제 발생!!!");
			request.setAttribute("error", "뉴스가 정상적으로 조회되지 않았습니다.");
			return listNews(request); // 오류발생시 다시 리스트 페이지로 이동처리
		}
		return "ch10/newsForm.jsp"; // 뉴스페이지로 이동
	}
   //뉴스 수정 처리
   public String updateNews(HttpServletRequest request) {
	   News n = new News();
	   try {
		   //이미지 파일 저장
		   Part part = request.getPart("file");
		   //이미지 파일 이름 얻기
		   String fileName = getFilename(part);
		   
		   System.out.println("파일전송여부:"+((fileName !=null && !fileName.isEmpty())?"파일전송됨"+fileName: "파일전송안됨"));
		   
		   //파일업로드 처리시 진행
		   if(fileName !=null && !fileName.isEmpty()) part.write(fileName);
		   
		   //<input>태그에 입력된 값 News객체 매핑
		   BeanUtils.populate(n, request.getParameterMap());//페이로드된 값들을 News속성에 설정
		   //이미지 파일 이름을 News객체 저장
		   n.setImg("/img/"+fileName);
		   //BD에 update처리
		   if(fileName !=null && !fileName.isEmpty()) 
		   {
			   n.setImg("/img/"+fileName);
		   }else {
			   n.setImg(null); 
		   }
		   //DB처리
		   dao.updateNews(n);
		   
	} catch (Exception e) {
		e.printStackTrace();
		ctx.log("뉴스 수정 과정에서 문제 발생!");
		request.setAttribute("error", "뉴스가 정상적으로 수정되지 않았습니다.");
		return listNews(request); //오류시 목록 페이지로 이동 처리
	}
	   //News객체에서 aid추출하여 변수에 저장
		   int aid = n.getAid();
	   //수정 완료 후 상세 페이지로 이동 처리
	   return "redirect:/news.nhn?action=getNews&aid="+aid;
   }
   
	
	
   
    //multipart 헤더에서 파일이름 추출
    private String getFilename(Part part) {
       String fileName = null;
       //파일 이름이 들어있는 헤더 영역을 가지고 옴
       String header = part.getHeader("content-disposition");
       System.out.println("Header => "+header);
       
       //파일 이름이 있는 속성 부분으이 시작위치를 가져와 쌍따옴표사이의 값 부분만 얻음.
       int start = header.indexOf("filename=");
       fileName = header.substring(start+10, header.length()-1);
       ctx.log("파일명:"+fileName);
       return fileName;
    }
   

 }
