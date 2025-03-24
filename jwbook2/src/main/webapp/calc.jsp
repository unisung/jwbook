<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="calc" class="jwbook2.model.Calculator"></jsp:useBean>
<jsp:setProperty name="calc" property="*" />   <!-- 파라미터로 넘어온 값이 속성에 자동 설정 -->
<jsp:getProperty name="calc" property="n1" />
<jsp:getProperty name="calc" property="n2" />
<jsp:getProperty name="calc" property="op" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기</title>
</head>
<body>
<h2>계산 결고-useBean</h2>
<hr>
결과: <%=calc.calc() %>
</body>
</html>