<%@page import="jwbook2.model.Member"%>
<%@page import="java.util.HashMap"%><%@page import="java.util.Map"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int[] myList = {1,2,3,4,5};  request.setAttribute("myList", myList); %>
<% Map<String,String> scoreMap = new HashMap<String,String>();
	scoreMap.put("name","일지매"); scoreMap.put("address","서울시");
	request.setAttribute("myMap",scoreMap );
%>
<jsp:useBean id="m"  class="jwbook2.model.Member"></jsp:useBean>
<jsp:setProperty  name="m" property="id" value="1"/>
<jsp:setProperty  name="m" property="name" value="홍길동"/>
<jsp:setProperty  name="m" property="email" value="hong@naver.com"/>

<% Member m1 = new Member(); m1.setId(3); m1.setName("일지매"); m1.setEmail("ilji@daum.net"); 
   request.setAttribute("m1", m1);
   Member m2 = new Member(); m2.setId(2); m2.setName("임꺽정"); m1.setEmail("kk@daum.net"); 
   session.setAttribute("m1", m2);
%>
<hr>
${sessionScope.m1.name }<br>
${requestScope.m1.name }<br>
<hr>
이름:<%= m.getName() %><br>
이름:<jsp:getProperty name="m" property="name"/><br>
이름:${m.name}<br>
<hr>
${10+20 }<br>
${10*20 }<br>
${true && false }<br>
${10 >=20 }<br>
${m.name == "홍길동"?"교수":"학생"} <br>
${myList[0] } <br>
${myMap["name"]}<br>
</body>
</html>