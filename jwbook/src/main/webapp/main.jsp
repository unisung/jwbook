<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
<h2>여기가 메인페이지입니다!!!</h2>
<h3>환영합니다<%=session.getAttribute("user")%>님</h3>
</body>
</html>