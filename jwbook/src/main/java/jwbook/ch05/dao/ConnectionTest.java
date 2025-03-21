package jwbook.ch05.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jwbook.ch05.model.Post;

public class ConnectionTest {
	public static void main(String[] args) throws SQLException {
		//DBConnection dbConn = new DBConnection();
		//디자인 패턴 중 ingleton방식으로 생성
		DBConnection dbConn = DBConnection.getInstance();		
		Connection con = dbConn.getCon();
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM post");
		List<Post> list = new ArrayList<>();
		
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
		
		for(Post p:list) {
			System.out.println(p);
		}
		rs.close(); stmt.close(); con.close();
	}
}
