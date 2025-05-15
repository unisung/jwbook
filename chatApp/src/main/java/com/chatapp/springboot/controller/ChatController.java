package com.chatapp.springboot.controller;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import com.chatapp.springboot.auth.SecurityConfig;
import com.chatapp.springboot.model.ChatMessage;
import com.chatapp.springboot.repository.ChatMessageRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {
	private final ChatMessageRepository chatMessageRepository;
	private final SimpMessagingTemplate simpMessagingTemplate;

	
	//메세지 전송
	@MessageMapping("/chat.send/{roomId}")
	//@SendTo("/topic/{roomId}")
	public void SendMessage(@DestinationVariable("roomId") String roomId,//전송할 룸
			                @Payload ChatMessage message,//전달 메세지객체
			                Principal principal) { //사용자 정보
		ChatMessage saved = null;
		System.out.println("📩 채팅 수신됨: " + message.getContent() + " by " + principal.getName());
		message.setRoomId(roomId);
		message.setSender(principal.getName());
		message.setTimestamp(LocalDateTime.now());
		message.setType("CHAT");
		
		
		System.out.println("👉 저장 전 메시지: " + message);
		try {
		    saved = chatMessageRepository.save(message);
		    System.out.println("✅ 저장됨: " + saved.getId());
		} catch (Exception e) {
		    System.out.println("❌ 저장 실패:");
		    e.printStackTrace();
		}
		
		// ✅ MongoDB에 저장
		//ChatMessage saved = chatMessageRepository.save(message);		
		// ✅ 대상 채팅방으로 메시지 브로드캐스트
		simpMessagingTemplate.convertAndSend("/topic/chatroom/" + roomId, saved);
	}
	
	//채팅방 입장시 메세지
	@MessageMapping("/chat.join/{roomId}")
	//@SendTo("/topic/{roomId}")
	public void joinRoom(@DestinationVariable("roomId") String roomId, 
			                    Principal principal) {
		ChatMessage joinMessage = new ChatMessage();
		joinMessage.setRoomId(roomId);
		joinMessage.setSender(principal.getName());
		joinMessage.setContent("님이 입장하였습니다.");
		joinMessage.setType("JOIN");
		joinMessage.setTimestamp(LocalDateTime.now());
		
		chatMessageRepository.save(joinMessage);		
		simpMessagingTemplate.convertAndSend("/topic/chatroom/" + roomId, joinMessage);
	}
	
	//채팅방 퇴장 메세지
	@MessageMapping("/chat.leave/{roomId}")
	public void leaveRoom(@DestinationVariable("roomId") String roomId, 
				                    Principal principal) {
		ChatMessage leaveMessage = ChatMessage.builder()
				                 .roomId(roomId)
				                 .sender(principal.getName())
				                 .type("LEAVE")
				                 .timestamp(LocalDateTime.now())
				                 .content(principal.getName()+"님이 퇴장하였습니다.")
				                 .build();

			chatMessageRepository.save(leaveMessage);
			simpMessagingTemplate.convertAndSend("/topic/chatroom/" + roomId, leaveMessage);
		}
	
	

}
