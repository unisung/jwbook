<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>채팅방 목록</title>
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
    <h2 class="mb-4 text-center">채팅방 목록</h2>

    <form action="/chatrooms" method="post" class="d-flex justify-content-center mb-4">
        <input type="text" name="name" class="form-control w-50 me-2" placeholder="새 채팅방 이름 입력" required>
        <button type="submit" class="btn btn-primary">채팅방 생성</button>
    </form>

    <ul class="list-group">
        <c:forEach var="room" items="${chatRooms}">
            <li class="list-group-item d-flex justify-content-between align-items-center">
                ${room.name}
                <a href="/chatrooms/${room.id}" class="btn btn-sm btn-outline-primary">입장</a>
            </li>
        </c:forEach>
    </ul>

    <div class="text-center mt-4">
        <a href="/" class="btn btn-secondary">← 홈으로</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
