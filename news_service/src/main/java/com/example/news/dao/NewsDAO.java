package com.example.news.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.news.model.News;

@Component
public class NewsDAO {
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	final String JDBC_USER = "jwbook";
	final String JDBC_PASSWORD = "1234";
	
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
	
	

}
