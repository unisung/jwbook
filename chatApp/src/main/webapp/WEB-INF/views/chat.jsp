<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>${room.name} 채팅방</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h2 class="text-center mb-4">${room.name} 채팅방</h2>

    <!-- 채팅 메시지 영역 -->
   <div id="messages" class="chat-box border rounded p-3 mb-3 d-flex flex-column">
   <c:forEach var="msg" items="${chatMessages}">
       <div class="p-2 rounded mb-2 w-auto
         ${msg.sender == userName ? 'align-self-end bg-primary text-white'
                       : 'align-self-start bg-warning text-dark'}">
         [${msg.type}] ${msg.sender} : ${msg.content}
       </div>
   </c:forEach>
   </div>

	<!-- 메시지 입력 및 버튼 영역 -->
	<form  id="chat-form" class="input-group mb-3">
	 <input id="messageInput" class="form-control" placeholder="메세지를 입력하세요" autocomplete="off">
	 <button type="submit" class="bt btn-primary">전송</button>
	</form>

	<!-- 퇴장 버튼 -->
	<div class="text-center">
	 <button id="leaveBtn" class="btn btn-danger">퇴장</button>
	 <a href="/chatrooms" class="btn btn-secondary"> 채팅방 목록으로</a>
	</div>

</div>
<script>
    
</script>
</body>
</html>
