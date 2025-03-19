package jwbook.ch05;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwbook.ch05.model.Post;

@WebServlet(description = "게시글리스트처리서블릿", urlPatterns = { "/mlist" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html; charset = utf-8");
//		response.getWriter().append("게시글 리스트 서블릿이에요~~~~~~~");
		//게시글 리스트 만들기
		List<Post> list = new ArrayList<Post>();
		int seq = 0;
		list.add(new Post(++seq, "위대하다", "밥을 많이 먹어서", LocalDateTime.now(), "lee", "lee@naver.com"));
		list.add(new Post(++seq, "나의 성격 유형", "infj", LocalDateTime.now(), "kim", "kim@naver.com"));
		list.add(new Post(++seq, "가을바람", "가을은 추남의 계절이다.", LocalDateTime.now(), "park", "park@naver.com"));
		list.add(new Post(++seq, "언젠가부터", "사람들과 이해관계가 힘들어지는거 같다.", LocalDateTime.now(), "lee", "lee@naver.com"));
		list.add(new Post(++seq, "node js는", "너무 재미있어요...", LocalDateTime.now(), "", ""));
		
		
		//for(Post post : list) {System.out.println(post.toString());}
		
		//"list" 속성명으로 list를 request에 저장
		request.setAttribute("list", list);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("mlist.jsp");
		dispatcher.forward(request, response);
	
	}

}
