package jwbook5.ch10.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		String sql = "select aid, title, substring(date,1,19) as cdate from news";
		//쿼리전달객체
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//쿼리실행 후 결과 받기
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			News  n = new News();
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setDate(rs.getString("cdate"));
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

	public News getNews(int aid) throws SQLException {
		News news = new News();
		Connection conn = open();
		String sql = "select aid, title, img, substring(date,1,19) as cdate, "
				   + " content from news where aid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
		   news.setAid(rs.getInt("aid"));
		   news.setTitle(rs.getString("title"));
		   news.setImg(rs.getString("img"));
		   news.setDate(rs.getString("cdate"));
		   news.setContent(rs.getString("content"));
		}
		return news;
	}

	//News 삭제
	public void deleteNews(int aid) throws SQLException {
		//1. DBMS와 연결 맺기
		Connection conn = open();
		//2. 삭제 쿼리문 작성
		String sql = "delete from news where aid=?";
		//3. 쿼리문 전달 객체 생성
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid);
		//4. 삭제 쿼리 실행 및 결과 처리
		pstmt.executeUpdate();
	}

	public void updateNews(News n) throws SQLException {
		// 1. DBMS와 연결 맺기
		Connection conn = open();
		// 2. 삭제 쿼리문 작성
		String sql;
		//PreparedStatement pstmt;
		Statement stmt;
		//문장내 인용부호('')가 있으면  이중인용부로호 변경("")
		n.setContent(n.getContent().replace("\'","\"" ));
		
		if (n.getImg() == null) {
			//sql = "update news set title=?, content=? where aid=?";
			sql = "update news set title='"+n.getTitle()+"',content='"+n.getContent()
			    +"' where aid="+n.getAid();
		} else {
			//sql = "update news set title=?, content=?, img=? where aid=?";
			sql = "update news set title='"+n.getTitle()+"',content='"+n.getContent()
			    +"', img='"+n.getImg()+"' where aid="+n.getAid();
		}
		System.out.println("쿼리:"+sql);
		// 3. 쿼리문 전달 객체 생성
		stmt = conn.createStatement();
//		pstmt = conn.prepareStatement(sql);
//		if (n.getImg() == null) {
//			pstmt.setString(1, n.getTitle());
//			pstmt.setString(2, n.getContent());
//			pstmt.setInt(3, n.getAid());
//		} else {
//			pstmt.setString(1, n.getTitle());
//			pstmt.setString(2, n.getContent());
//			pstmt.setString(3, n.getImg());
//			pstmt.setInt(4, n.getAid());
//		}
		// 4. 삭제 쿼리 실행 및 결과 처리
		stmt.execute(sql);
//		pstmt.executeUpdate();
	}
}
