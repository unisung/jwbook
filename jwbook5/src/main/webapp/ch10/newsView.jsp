<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>뉴스 관리 앱</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
    <div class="container w-75 mt-5 mx-auto">
     <h2>${news.title}</h2>
      <hr>
      <div class="card w-75 mx-auto">
	  <img src="${news.img}" class="card-img-top" alt="...">
	  <div class="card-body">
	    <h5 class="card-title">Date: ${news.date}</h5>
	    <p class="card-text">Content: ${news.content}</p>
	  </div><!-- card-body -->
      </div><!-- card -->
    </div><!-- container -->
    <hr>
    <a href="javascript:history.back()" class="btn btn-primary"><< Back</a>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>