package com.example.news.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Component;

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
	
	

}
