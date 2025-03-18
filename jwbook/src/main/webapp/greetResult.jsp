<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>hello world!!!</h1>
 <h3><%=request.getAttribute("msg") %></h3>
 <hr>
 <h4><%=request.getAttribute("user") %></h4>
 <h4><%=request.getAttribute("p1") %></h4>
 <h4><%=request.getAttribute("p2") %></h4>
 <h4><%=request.getAttribute("sel") %></h4>
</body>
</html>