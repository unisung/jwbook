package jwbook5.ch10.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jwbook5.ch10.model.News;

public class NewsDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	                        
	//db연결 메소드
	public Connection open() {
		Connection conn = null;
		 try {
			 Class.forName(JDBC_DRIVER);
			 conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
			 }catch(Exception e) {e.printStackTrace();}
		return conn;
	}

	//뉴스 기사 목록 전체  리턴 
	public List<News> getAll() throws Exception {
		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		//쿼리문작성
		String sql = "select aid, title, parsedatetime(date, 'yyyy-MM-dd hh:mm:ss') as cdate from news";
		//쿼리전달객체
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//쿼리실행 후 결과 받기
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			News  n = new News();
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setDate(rs.getString("cdata"));
			//개별뉴스 한건씩 list에 저장
			newsList.add(n);
		}
		return newsList;
	}

	//뉴스정보 저장
	public void addNews(News n) throws SQLException {
		Connection conn = open();
		String sql ="insert into news(title, img, date, content) values(?,?,current_timestamp(),?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		try(conn; pstmt) {
			pstmt.setString(1, n.getTitle());
			pstmt.setString(2, n.getImg());
			pstmt.setString(3, n.getContent());
			pstmt.executeUpdate();
		}
	}
}
