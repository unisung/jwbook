package com.chatapp.springboot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatapp.springboot.model.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage,String>{
	//채팅방별로 기록된 채팅메시지 얻기
	List<ChatMessage> findByRoomIdOrderByTimestampAsc(String roomId);
}
