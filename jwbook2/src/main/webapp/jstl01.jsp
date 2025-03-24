<%@ page  contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<% pageContext.setAttribute("msg","user1"); // page범위에 저장 %>
<body>
<c:if test="${1==1}">
  hello~~~
</c:if>
<hr>
<c:if test="${2!=1}">
  hello~~~
</c:if>
<hr>
<c:if test="${msg == 'user1' }" var="result">
  test result : ${result}
</c:if>
</body>
</html>