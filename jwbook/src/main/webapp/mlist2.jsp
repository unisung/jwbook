<%@page import="jwbook.ch05.model.Post"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>mlist페이지 입니다!</h1>
<!-- 스크립틀릿 :자바코드로 로직 구현하는 공간 -->
<ul>
<%
  List<Post> list = (List<Post>)request.getAttribute("list");
  for(Post p:list){ %>
   <li><% out.print(p); %></li>
   <%-- <li><%=p %></li> --%>
<%	  
  }
%>
</ul>
</body>
</html>