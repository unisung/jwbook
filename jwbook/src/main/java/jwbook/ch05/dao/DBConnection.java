package jwbook.ch05.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DBConnection {
	//DB연결 객체
	 Connection con=null;
	 ResultSet rs=null;
	 //DBMS연결정보
	 private String url ="jdbc:mysql://127.0.0.1:3306/myboard";
	 private String user ="root";
	 private String password="root";
	 
     private static DBConnection instance = new DBConnection();//자기 자신을 필드로 선언
     //자기 자신의 인스턴스를 리턴하는 메소드 
	 public static DBConnection getInstance() {
		return instance;
	}
	 //생성자
	 private DBConnection() {
		 try {
			 //라이브러리에 저장된 Driver파일을 메모리로 로딩처리 Class.forName()
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(url, user, password);
			 System.out.println(con);
			 System.out.println("데이터베이스 접속 성공");
		 }catch(Exception e) {
			 System.out.println(e.getMessage());
		 }
	 }
	 
	//연결 객체 얻기
	public Connection getCon() {
		return con;
	}
}
