<%@page import="jwbook2.model.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>useBean</title>
</head>
<body>
<!--  -->
<% Member mem2 = new Member(); 
   mem2.setEmail("kim");
   mem2.setName("김길동");
   mem2.setId(2);
%>
<%=mem2.getId() %> :<%=mem2.getName()%> : <%=mem2.getEmail() %> <br>

<hr>

<jsp:useBean id="mem" class ="jwbook2.model.Member"  scope="page"></jsp:useBean>
<jsp:setProperty name="mem" property="id" value="1" />
<jsp:setProperty name="mem" property="name" value="홍길동" />
<jsp:setProperty name="mem" property="email" value="hong@daum.net" />

<jsp:getProperty  name="mem" property="id"/><br>
<jsp:getProperty  name="mem" property="name"/><br>
<jsp:getProperty  name="mem" property="email"/><br>

</body>
</html>