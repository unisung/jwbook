package com.example.news.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.example.news.controller.TestController;
import com.example.news.model.News;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Component
public class NewsDAO {

    private final TestController testController;
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	final String JDBC_USER = "jwbook";
	final String JDBC_PASSWORD = "1234";

    NewsDAO(TestController testController) {
        this.testController = testController;
    }
	
	//JAVA와 DB 데이타 및 쿼리 관리객체
	public Connection open() {
		Connection conn=null;
		try {
			Class.forName(JDBC_DRIVER);//
			conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public List<News> getAll() throws Exception{
		Connection conn=open();
		List<News> newsList = new ArrayList<News>();
		//쿼리문 작성
		String sql = "select aid, title, substring(date,1,19) as cdate from news";
		//쿼리 전달객쳇
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//쿼리실행 후 결과 받기
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			News n = new News();
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setDate(rs.getString("cdate"));
			//개별뉴스 한 건씩 list에 저장
			newsList.add(n);
		}
		
		return newsList;
	}

	//뉴스 가져오기
	public News getNews(int aid) throws Exception{
		Connection conn=open();
		News n = new News();
		//쿼리문 작성
		String sql = "select aid, title, substring(date,1,19) as cdate, img, content from news where aid=?";
		//쿼리 전달객쳇
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, aid); // ?에 값 세팅
		//쿼리실행 후 결과 받기
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			n.setAid(rs.getInt("aid"));
			n.setTitle(rs.getString("title"));
			n.setDate(rs.getString("cdate"));
			n.setImg(rs.getString("img")); // 이미지 파일 항목
			n.setContent(rs.getString("content"));
		}
		return n;
	}

	//추가
	public void addNews(News news) throws Exception{
		Connection conn=open();
		//쿼리문 작성
		String sql = "insert into news(title, img, date, content) values(?,?,current_timestamp(),?)";
		//쿼리 전달객쳇
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, news.getTitle()); // ?에 값 세팅
		pstmt.setString(2, news.getImg()); // ?에 값 세팅
		pstmt.setString(3, news.getContent()); // ?에 값 세팅
		
		//쿼리실행 후 결과 받기
		 pstmt.executeUpdate();
	}

	//수정
	public void updateNews(News news) throws Exception{
	 String sql1 ="update news set title=?, content=? where aid=?";
	 String sql2 ="update news set title=?, content=?, img=? where aid=?";     System.out.println(news.getImg()==null?"이미지 없음":"이미지 있음");
	 
     Connection conn=open();
	 //쿼리객체 생성
     PreparedStatement pstmt = null; 
     //이미지 정보 유무에 따른 설정
     if(news.getImg()==null) {
    	 pstmt = conn.prepareStatement(sql1);
    	 pstmt.setString(1, news.getTitle());
    	 pstmt.setString(2, news.getContent());
    	 pstmt.setInt(3, news.getAid());
     }else {
    	 pstmt = conn.prepareStatement(sql2);
    	 pstmt.setString(1, news.getTitle());
    	 pstmt.setString(2, news.getContent());
    	 pstmt.setString(3, news.getImg());
    	 pstmt.setInt(4, news.getAid());
     }
      pstmt.executeUpdate();     
	}

	public void deleteNews(int aid) throws Exception{
		//DB연결 객체 얻기
	 Connection conn= open();
	 //쿼리문 작성
	 String sql = "delete from news where aid=?";
	 //쿼리 전달 객체 생성
	 PreparedStatement pstmt = conn.prepareStatement(sql);
	 //쿼리 전달 객체에 바인딩 변수 값 설정
	 pstmt.setInt(1, aid);
	 //DB 삭제처리
	 pstmt.executeUpdate();
	}
}
