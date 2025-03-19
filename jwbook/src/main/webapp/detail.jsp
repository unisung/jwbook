<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>상세 페이지</h1>
  <div class="card" style="width: 18rem;">
	  <div class="card-body">
	    <h5 class="card-title">${post.title}</h5>
	    <h6 class="card-subtitle mb-2 text-body-secondary">${post.writer}</h6>
	    <p class="card-text">${post.content}</p>
	    <a href="mlist" class="card-link">글목록</a>
	  </div>
</div>
</body>
</html>