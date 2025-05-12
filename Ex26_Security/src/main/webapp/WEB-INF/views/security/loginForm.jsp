<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1> loginForm.jsp</h1>
 <form action = "<c:url value="j_spring_security_check" />" method="post">
  ID:<input name="j_username"> <br>
  PW:<input type="password" name="j_password"> <br>
  <input type="submit" value="Login"><br>
 </form>
</body>
</html>