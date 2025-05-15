package com.chatapp.springboot.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chatapp.springboot.model.ChatRoom;
import com.chatapp.springboot.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
	private final ChatRoomRepository chatRoomRepository;
	
	//채팅방 생성
	public ChatRoom createRoom(String name, String creator) {
		ChatRoom room = ChatRoom.builder()
				        .name(name)
				        .creator(creator)
				        .createdAt(LocalDateTime.now())
				        .build();
		//생성된 채팅방 저장 및 리턴
		return chatRoomRepository.save(room);
	}
	
	//채팅방 목록 출력
	public List<ChatRoom> getAllRooms(){
		return chatRoomRepository.findAll();
	}
	
	//id별 채팅방 찾기
	public ChatRoom getRoom(String id) {
		return chatRoomRepository.findById(id).orElse(null);
	}

}
