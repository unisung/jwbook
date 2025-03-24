<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
  List<String> list = new Vector<String>();
  list.add("hello");list.add("hi");list.add("bye");
  pageContext.setAttribute("greetings", list);
%>
<c:forEach begin="0" end="2" step="1" items="${greetings}" var="s" varStatus="sts">
   ${s}<br>
</c:forEach>
<hr>
<c:forEach items="${greetings}" var="s" varStatus="sts">
  ${sts.index} - ${sts.count} - ${sts.first} - ${sts.last} - ${sts.current} - ${s}<br>
</c:forEach>
</body>
</html>