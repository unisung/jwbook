<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>뉴스 관리 앱</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">
  </head>
  <body>
    <div class="container w-75 mt-5 mx-auto">
    <h2>뉴스 목록</h2>
    <hr>
    <ul class="list-group">
     <c:forEach var="news" items="${newsList}" varStatus="status">
      <li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center ">
       <a href="/news/${news.aid}" class="text-decoration-none">[${status.count}]${news.title}, ${news.date}</a>
       <a href="/news/delete/${news.aid}">
         <span class="badge bg-secondary">&times;</span>
       </a>
     </li>
     </c:forEach>
    </ul> 
    <hr>
    <!-- 오류부분 -->
    <c:if test="${error !=null}">
    <div>
      에러 발생:${error}
      <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    </div>
    </c:if>
    <!-- 뉴스 등록 -->
    <button class="btn btn-outline-info mb-3" type="button" data-bs-toggle="collapse"
    data-bs-target="#addForm" aria-expanded="false" aria-controls="addForm">뉴스등록
    </button>
    <div class="collapse" id="addForm">
      <div class="card" style="width: 18rem;">
		  <div class="card-body">
		     <form method="post" action="/news/add" enctype="multipart/form-data">
		      	<label class="form-label">제목</label>
		      	<input name="title" class="form-control">
		      	<label class="form-label">이미지</label>
		      	<input name="file" type="file" class="form-control">
		      	<label class="form-label">기사내용</label>
		      	<textarea rows="5" cols="50" name="content" class="formn-control"></textarea>
		        <button type="submit" class="btn btn-success mt-3">저장</button>
		     </form>
		  </div><!-- card-body -->
      </div><!-- card -->
    </div>
    <!-- /뉴스 등록 -->
    </div><!-- container -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
  </body>
</html>