<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보 리스트</title>
</head>
<body>
 <h2>학생 정보 리스트</h2>[<a href="/jwbook4/studentControl">새로고침</a>]
 <table border="1">
 <tr><th>id</th><th>이름</th><th>대학</th><th>생일</th><th>이메일</th></tr>
 <c:forEach var="s" items="${students}">
 <tr>
 <td>${s.id}</td><td><a href="studentControl?action=info&id=${s.id}">${s.username}</a></td><td>${s.univ}</td><td>${s.birth}</td>
 <td>${s.email}</td>
 </tr>
 </c:forEach>
 </table>
 <hr>
  <h2>학생 추가</h2>
 <hr>
 <form method="post" action="/jwbook4/studentControl?action=insert">
  <label>이름</label>
  <input name="username" required="required"><br>
  <label>대학</label>
  <input name="univ" required><br>
  <label>생일</label>
  <input name="birth" type="date" required><br>
  <label>이메일</label>
  <input name="email" required><br>
  <button type="submit">등록</button>
 </form>
  
</body>
</html>