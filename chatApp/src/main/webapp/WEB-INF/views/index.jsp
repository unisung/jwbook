<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ChatApp - 홈</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<!-- ✅ 네비게이션 바 -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="/">ChatApp</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="/">홈</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/chatrooms">채팅방 목록</a>
                </li>
            </ul>
            <div class="d-flex">
                <c:choose>
                    <%-- 로그인한 경우 --%>
                    <c:when test="${not empty sessionScope.username}">
                        <span class="text-white me-3 align-self-center">
                            👋 ${sessionScope.username} 님
                        </span>
                        <a href="/logout" class="btn btn-outline-light btn-sm me-2">로그아웃</a>
                        <a href="/mypage" class="btn btn-outline-light btn-sm">마이페이지</a>
                    </c:when>
                    <%-- 로그인하지 않은 경우 --%>
                    <c:otherwise>
                        <a href="/login" class="btn btn-outline-light btn-sm me-2">로그인</a>
                        <a href="/register" class="btn btn-outline-light btn-sm">회원가입</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</nav>

<!-- ✅ 메인 콘텐츠 -->
<div class="container text-center py-5">
    <h1 class="display-5 fw-bold mb-3">채팅 애플리케이션에 오신 것을 환영합니다!</h1>
    <p class="lead text-secondary">다양한 사람들과 자유롭게 실시간으로 채팅을 나누어보세요.</p>
    <a href="/chatrooms" class="btn btn-success btn-lg mt-4">
        👉 채팅 시작하기
    </a>
</div>

</body>
</html>
