package jwbook.ch05;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwbook.ch05.dao.DBConnection;
import jwbook.ch05.model.Post;

@WebServlet("/mdetail")
public class PostDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
        
		//DB에서가져와서
        DBConnection dbConn = DBConnection.getInstance();
        Connection conn = dbConn.getCon();
        String sql = "select * from post where id="+id;
        System.out.println("sql문:"+sql);
        
        Statement stmt=null;
        ResultSet rs=null;
        Post  post=null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date created = rs.getDate("created");
				String writer = rs.getString("writer");
				String email = rs.getString("email");
				
				LocalDateTime localDateTime 
				= new java.sql.Timestamp(created.getTime()).toLocalDateTime();
				
				post = new Post(Integer.parseInt(id), title, content, localDateTime, writer, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//해당 id번호의 Post객체를 detail.jsp로 넘김
	    //과제 - 선착순 3명 - challenge
		request.setAttribute("post", post);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("detail.jsp");
		dispatcher.forward(request, response);
	}
}
