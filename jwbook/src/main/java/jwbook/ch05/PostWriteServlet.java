package jwbook.ch05;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwbook.ch05.dao.DBConnection;
import jwbook.ch05.model.Post;


@WebServlet("/write")
public class PostWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//writeForm.jsp로 이동처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//forward
		RequestDispatcher dispatcher = request.getRequestDispatcher("writeForm.html");
		dispatcher.forward(request, response);
	}

	//writeForm.jsp로 부터 넘어온 파라미터 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
		//파라미터로 넘어온 값 title, witer, content, email을 받아서 post추가하고
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String email = request.getParameter("email");

		//DB에서가져와서
        DBConnection dbConn = DBConnection.getInstance();
        Connection conn = dbConn.getCon();
        String sql = "insert into post(title,content,created,writer,email) "
        		   + " values('"+title+"','"+content+"','"+LocalDateTime.now()+"','"+writer+"','"+email+"')";
        System.out.println("sql문:"+sql);
        
        Statement stmt=null;
        ResultSet rs=null;
		
        try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		// /mlist로 전달 localhost:8080/jwbook/mlist
		response.sendRedirect("mlist");
	}
}
