<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
  welcome : member
  
  <c:if test="${not empty pageContext.request.userPrincipal}">
    <p> is Log-In </p>
  </c:if>

  <c:if test="${  empty pageContext.request.userPrincipal}">
    <p> is Log-Out </p>
  </c:if>
  
   <sec:authorize access="isAuthenticated()">
   <p> Log-In </p>
  </sec:authorize>
  
  <sec:authorize access="!isAuthenticated()">
   <p> Log-Out </p>
  </sec:authorize>
  
  User ID: ${pageContext.request.userPrincipal.name}<br>
  USER ID :<sec:authentication property="name"/><br>
  <a href="/logout">Log Out</a><br>
  
</body>
</html>