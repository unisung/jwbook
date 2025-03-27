package jwbook4.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jwbook4.model.Student;

public class StudentDAO {
    //연결객체  
	Connection conn = null;
    //sql전달객체
	PreparedStatement pstmt;
    //
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/~/jwbookdb";
	final String USERNAME = "jwbook";
	final String PASSWORD = "1234";
	
	//접속 메소드
	public void open() {
		try {
			//드라이버 로딩
			Class.forName(JDBC_DRIVER);
			//연결객체 얻기
			conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//연결해제 메소드
	public void close() {
		try {
			pstmt.close();//sql문 전달객체 해제
			conn.close(); //연결객체 해제
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> getAll() {
		//1.DBMS와 연결후 Connection객체 얻기
		open();
		List<Student> students = new ArrayList<>();
		try {
			//2. 쿼리전달객체 생성
			String sql = "select * from student";
			pstmt = conn.prepareStatement(sql);
			//3. 조회결과Set받기
			ResultSet rs = pstmt.executeQuery();
			//4. 결과 처리
			
			while(rs.next()) {
				int index = 1;
				Student s = new Student();
				s.setId(rs.getInt(index++));
				s.setUsername(rs.getString(index++));
				s.setUniv(rs.getString(index++));
				s.setBirth(rs.getDate(index++));
				s.setEmail(rs.getString(index++));
				students.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		//DBMS로 부터 전달받은 ResultSet으로 부터 추출한 학생정보리스트
		return students;
	}
}
