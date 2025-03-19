<%@page import="jwbook.ch05.model.Post"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
</head>
<body>
<h1>mlist페이지 입니다!</h1>
<table class="table table-striped">
<tr>
 <th>id</th><th>제목</th><th>작성일자</th><th>작성자</th>
</tr>
<c:forEach var="p" items="${list}">
	<tr> 
	 <td>${p.id}</td><td><a href="mdetail?id=${p.id}">${p.title}</a></td><td>${p.created}</td><td>${p.writer}</td>
	</tr>
</c:forEach>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>