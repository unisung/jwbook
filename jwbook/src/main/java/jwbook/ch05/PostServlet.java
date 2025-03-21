package jwbook.ch05;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwbook.ch05.dao.DBConnection;
import jwbook.ch05.model.Post;

@WebServlet(description = "게시글리스트처리서블릿", urlPatterns = { "/mlist" })
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 리스트 만들기
		List<Post> list = new ArrayList<Post>();
		//DB에서가져와서
        DBConnection dbConn = DBConnection.getInstance();
        Connection conn = dbConn.getCon();
        String sql = "select * from post";
        Statement stmt=null;
        ResultSet rs=null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM post");
			while(rs.next()) {
				Post  post;
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date created = rs.getDate("created");
				String writer = rs.getString("writer");
				String email = rs.getString("email");
				
				LocalDateTime localDateTime 
				= new java.sql.Timestamp(created.getTime()).toLocalDateTime();
				
				post = new Post(id, title, content, localDateTime, writer, email);
				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//request에 저장
		request.setAttribute("list", list);
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("mlist.jsp");
		dispatcher.forward(request, response);
	
	}

}
