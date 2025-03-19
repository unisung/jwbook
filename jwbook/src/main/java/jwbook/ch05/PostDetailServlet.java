package jwbook.ch05;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwbook.ch05.model.Post;


@WebServlet("/mdetail")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 리스트 만들기
		List<Post> list = new ArrayList<Post>();
		int seq = 0;
		list.add(new Post(++seq, "위대하다", "밥을 많이 먹어서", LocalDateTime.now(), "lee", "lee@naver.com"));
		list.add(new Post(++seq, "나의 성격 유형", "infj", LocalDateTime.now(), "kim", "kim@naver.com"));
		list.add(new Post(++seq, "가을바람", "가을은 추남의 계절이다.", LocalDateTime.now(), "park", "park@naver.com"));
		list.add(new Post(++seq, "언젠가부터", "사람들과 이해관계가 힘들어지는거 같다.", LocalDateTime.now(), "lee", "lee@naver.com"));
		list.add(new Post(++seq, "node js는", "너무 재미있어요...", LocalDateTime.now(), "", ""));
		//파라미터정보 받기
		int id = Integer.parseInt(request.getParameter("id")); //파라미터는 String으로 넘어옴 "1"
		Post post = list.get(id-1);
		System.out.println(post);
		
		//해당 id번호의 Post객체를 detail.jsp로 넘김
	    //과제 - 선착순 3명 - challenge
	}
}
