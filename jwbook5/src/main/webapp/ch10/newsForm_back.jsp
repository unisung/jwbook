<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>뉴스 수정 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
    <div class="container w-75 mt-5 mx-auto">
    <form action = "/jwbook5/news.nhn?action=updateNews" 
	      method = "post" 
	      enctype="multipart/form-data">
    <input type = "hidden" name="aid" value = "${news.aid }">
     <h2 class = "card-title"><input name = "title" class = "form-control" value = "${news.title}" style = "width = 100%" class = "h2"></h2>
      <hr>
      <div class="card w-75 mx-auto">
     <img src="${news.img}" class="card-img-top" alt="...">
     <input type = hidden name = imageName value = "${news.img}">
      <input type = "file" name="file" class="form-control">
     <div class="card-body">
       <h5 class="card-title">Date: ${news.date}</h5>
       <p class="card-text">Content: </p>
       <textarea rows="5" cols="50" name="content" class="form-control">${news.content}</textarea>
     </div><!-- card-body -->
      </div><!-- card -->
      <button type = "submit" class = "btn btn-danger">수정</button>
      </form> <!-- form  -->
    </div><!-- container -->
    <hr>
    <a href="javascript:history.go(-1)" class="btn btn-primary"><< Back</a>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>