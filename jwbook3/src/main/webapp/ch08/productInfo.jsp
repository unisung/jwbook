<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 페이지</title>
</head>
<body>
<h2>상품 상세 페이지</h2>
 <hr>
 <ul>
 	<li>상품코드:${p.id}</li>
 	<li>상품명:${p.name}</li>
 	<li>제조사:${p.maker}</li>
 	<li>가격:<fmt:formatNumber value="${p.price}" type="currency"/>  </li>
 	<li>등록일:${p.date}</li>
 </ul>
</body>
</html>