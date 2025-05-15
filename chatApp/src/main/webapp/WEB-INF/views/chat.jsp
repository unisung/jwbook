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
    let stompClient = null;
    const roomId ="${room.id}";
    const currentUser = "${userName}"
    //채팅방으로 이동시 이벤트처리
    document.addEventListener("DOMContentLoaded",function(){
    	//소켓과 stomp클라이언트 객체 생성
    	const socket = new SockJS("/ws");
    	stompClient = Stomp.over(socket);
    	//연결
    	stompClient.connect({}, function(frame){
    		stompClient.subscribe("/topic/chatroom/" + roomId, function(msg){
    			const message = JSON.parse(msg.body);
    			//채팅방의 영역에 메세지 추가
    			appendMessage(message);
    		});
    		
    		//입장 메시지
    		stopmClient.send("/app/chat.join/" + roomId, {}, {});
    	});
    	
    	//메시지 전송
    	document.getElementById("chat-form").addEventListener("submit",function(e){
    		e.preventDefault(); //기본 동작 막기
    		const input = document.getElementById("messageInput");
    		const content = input.value.trim();//input태그의 입력된 value값 얻기
    		if(content){
    			//메시지 전송
    			stompClient.send("/app/chat.send/" + roomId, {}, JSON.stringify({content}));
    			//메시지 입력칸 클리어
    			input.value = "";
    		}
    	});//메시지 전송 끝.
    	
    	//퇴장 처리 -- challenge-2
    	
    	//채팅방영역에 메세지 추가
    	function appendMessage(message){
    		const messages = document.getElementById("messages");
    		const bubble = document.createElement("div");
    		const isMe = message.sender === currentUser; // 메세지 좌우 배치용(색상구분)
    		//출력 템플릿
    		bubble.innerText = `[\${message.type}] \${message.sender}: \${message.content}`;
    		
    		//기본 스타일
    		bubble.classList.add("p-2","rounded", "mb-2", "w-auto", "d-inline-block");
    		
    		//방항 및 색상 스타일 분기
    		if(isMe){
    			bubble.classList.add("align-self-end", "bg-primary", "text-white");
    		}else{
    			bubble.classList.add("align-self-start", "bg-warning", "text-dark");
    		}
    		
    		//채팅방영역에 추가
    		messages.appendChild(bubble);
    		//스크롤 이동
    		messages.scrollTop = message.scrollHeight;
    	}
    });// 끝.
    
</script>
</body>
</html>
