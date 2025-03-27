<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 정보 리스트</title>
</head>
<body>
 <h2>학생 정보 리스트</h2>
 <table border="1">
 <tr><th>id</th><th>이름</th><th>대학</th><th>생일</th><th>이메일</th></tr>
 <c:forEach var="s" items="${students}">
 <tr>
 <td>${s.id}</td><td>${s.username}</td><td>${s.univ}</td><td>${s.birth}</td>
 <td>${s.email}</td>
 </tr>
 </c:forEach>
 </table>
</body>
</html>