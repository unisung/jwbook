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
	
	public void insert(Student s) {
		//DBMS연결 객체 얻기
		open();
		//쿼리문작성 - 입력쿼리 template
		String sql = "insert into student(username, univ, birth, email) values(?,?,?,?)";
		try {
		//쿼리전달객체 생성
		pstmt = conn.prepareStatement(sql);
		//바인딩변수(?) 순서대로 값 설정하기
		int i=1;
		pstmt.setString(i++,s.getUsername());
		pstmt.setString(i++,s.getUniv());
		pstmt.setDate(i++,s.getBirth());
		pstmt.setString(i++,s.getEmail());
		//퀄리 실행 후 결과 처리
		pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Student getInfo(String id) {
		//1.DBMS와 연결후 Connection객체 얻기
		open();
		Student s = new Student();
		try {
			//2. 쿼리전달객체 생성
			String sql = "select * from student where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			//3. 조회결과Set받기
			ResultSet rs = pstmt.executeQuery();
			//4. 결과 처리
			while(rs.next()) {
				int index = 1;
				s.setId(rs.getInt(index++));
				s.setUsername(rs.getString(index++));
				s.setUniv(rs.getString(index++));
				s.setBirth(rs.getDate(index++));
				s.setEmail(rs.getString(index++));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		//DBMS로 부터 전달받은 ResultSet으로 부터 추출한 학생정보리스트
		return s;
	}
	
	public void update(Student s) {
		// DBMS연결 객체 얻기
		open();
		// 쿼리문작성 - 입력쿼리 template
		String sql = "update student set username=?,univ=?,birth=?,email=? where id=?";
		try {
			// 쿼리전달객체 생성
			pstmt = conn.prepareStatement(sql);
			// 바인딩변수(?) 순서대로 값 설정하기
			int i = 1;
			pstmt.setString(i++, s.getUsername());
			pstmt.setString(i++, s.getUniv());
			pstmt.setDate(i++, s.getBirth());
			pstmt.setString(i++, s.getEmail());
			pstmt.setInt(i++, s.getId());

			// 쿼리 실행 후 결과 처리
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		// DBMS연결 객체 얻기
		open();
		// 쿼리문작성 - 입력쿼리 template
		String sql = "delete from student where id=?";
		try {
			// 쿼리전달객체 생성
			pstmt = conn.prepareStatement(sql);
			// 바인딩변수(?) 순서대로 값 설정하기
			pstmt.setInt(1, Integer.parseInt(id));
			// 쿼리 실행 후 결과 처리
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
