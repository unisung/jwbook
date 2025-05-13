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
 <form action="<c:url value="j_spring_security_check" />" method="post">
	<c:if test="${param.error != null}">
	<p>
		Login Error! <br />
		${error_message}
	</p>
	</c:if>
	
	ID : <input type="text" name="j_username" value="${username}"> <br />
	PW : <input type="password" name="j_password"> <br />
	<input type="submit" value="LOGIN"> <br />
</form>

</body>
</html>