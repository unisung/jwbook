package com.chatapp.springboot.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "chat_message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
	@Id
	private String id;
	private String roomId;        // 채팅방 ID
    private String sender;        // 보낸 사람
    private String content;       // 메시지 본문
    private String type;          // 메시지 타입: CHAT, JOIN, LEAVE
    private LocalDateTime timestamp;  // 전송 시각

}
