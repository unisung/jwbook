<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Hello World</h2>
<hr>
현재 날짜와 시간은 <%=java.time.LocalDateTime.now() %>입니다. <br>
<%=request.getAttribute("msg") %> <br>
<h2>${msg}</h2>
</body>
</html>