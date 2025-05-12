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
       <%-- <a href="javascript:deleteConfirm(${news.aid})"> --%>
		 <!-- Button trigger modal -->
		<button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal"
		 data-aid="${news.aid}">
		         <span class="badge bg-secondary">&times;</span>
		</button>
       <!-- </a> -->
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
      <div class="card text-bg-warning" style="width: 36rem;">
		  <div class="card-body ">
		     <form method="post" action="/news/add" enctype="multipart/form-data">
		      	<label class="form-label">제목</label>
		      	<input name="title" class="form-control">
		      	<label class="form-label">이미지</label>
		      	<input name="file" type="file" class="form-control">
		      	<label class="form-label">기사내용</label>
		      	<textarea rows="5" cols="50" name="content" class="form-control"></textarea>
		        <button type="submit" class="btn btn-success mt-3">저장</button>
		     </form>
		  </div><!-- card-body -->
      </div><!-- card -->
    </div>
    <!-- /뉴스 등록 -->
    </div><!-- container -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">해당뉴스 기사 삭제여부 확인</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        정말 삭제 하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id="deleteBtn">삭제</button>
      </div>
    </div>
  </div>
</div> <!-- Modal -->

<script>
 const exampleModal  = document.getElementById('exampleModal');
 //모달영역이 보여질때 이벤트 처리
 exampleModal.addEventListener('show.bs.modal', function(event){
	 const button = event.relatedTarget; // 모달을 보여지게한 버튼
	 const aid = button.getAttribute('data-aid'); // data-aid값을 얻기
	 console.log('전달된 aid:', aid);
	 
const deleteButton = document.getElementById('deleteBtn');
 deleteButton.setAttribute('data-aid',aid);
   //삭제 버튼 클릭시 '/news/delete/뉴스번호'로 이동처리
   deleteButton.addEventListener('click',function(){
	   window.location.href = '/news/delete/' + aid;
   });
  
 })
</script>

  </body>
</html>