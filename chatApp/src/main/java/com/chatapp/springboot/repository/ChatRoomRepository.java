package com.chatapp.springboot.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatapp.springboot.model.ChatRoom;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
	

}
