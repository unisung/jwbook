<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">ChatApp</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="/">홈</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/chatrooms">채팅방 목록</a>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/login">로그인</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/register">회원가입</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="text-center mb-4">회원가입</h3>

                    <form method="post" action="/register">
                        <div class="mb-3">
                            <label for="username" class="form-label">아이디</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">비밀번호</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>

                        <button type="submit" class="btn btn-success w-100">가입</button>
                    </form>

                    <c:if test="${not empty param.error}">
                        <div class="alert alert-danger mt-3" role="alert">
                            회원가입 실패: 이미 존재하는 아이디입니다.
                        </div>
                    </c:if>

                    <p class="mt-3 text-center">
                        이미 계정이 있으신가요?
                        <a href="/login" class="btn btn-link">로그인</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
