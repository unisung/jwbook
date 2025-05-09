<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>뉴스 수정 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.12.1/font/bootstrap-icons.min.css"> 
</head>
  <body>
    <div class="container w-75 mt-5 mx-auto">

    <form action="/news/update" 
           method="post" 
           enctype="multipart/form-data">
      <input type="hidden" name="aid" value="${news.aid}">
     <input name="title" value='${news.title}' style="width:100%"  class="h2">
      <hr>
      <div class="card w-75 mx-auto">
	  <img src="${news.img}" class="card-img-top" alt="...">
	  <input type="file" name="file" class="form-control">
	  <div class="card-body">
	    <h5 class="card-title">Date: ${news.date}</h5>
	    <p class="card-text">Content: 
	    <textarea rows="5" cols="50" name="content" class="form-control">${news.content}</textarea>
	    </p>
	  </div><!-- card-body -->
      </div><!-- card -->
      <button type="submit" class="btn btn-warning">수정</button>
   </form><!-- form -->
    </div><!-- container -->
    <hr>
    <a href="javascript:history.back()" class="btn btn-primary"><i class="bi bi-chevron-double-left"></i> Back</a>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>