package com.chatapp.springboot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatapp.springboot.model.ChatMessage;
import com.chatapp.springboot.model.ChatRoom;
import com.chatapp.springboot.repository.ChatMessageRepository;
import com.chatapp.springboot.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatRoomController {
	
	private final ChatRoomService chatRoomService;
	private final ChatMessageRepository chatMessageRepository;
	
	//채팅방 리스트
	@GetMapping("/chatrooms")
	public String listRooms(Model model) {
		List<ChatRoom> rooms = chatRoomService.getAllRooms();//채팅방리스트 얻기
		model.addAttribute("chatRooms", rooms);
		return "chatrooms"; // chatrooms.jsp
	}
	
	//채팅방 생성
	@PostMapping("/chatrooms")
	public String createRoom(@RequestParam("name") String name, Principal principal) {
	    chatRoomService.createRoom(name, principal.getName());//createRoom(룸명,생성자);
	    return "redirect:/chatrooms"; //
	}
	
	//채팅방 입장
	@GetMapping("/chatrooms/{id}") // {id}는 채팅방 id
	public String enterRooms(@PathVariable("id") String id,  Model model, Principal principal) {
		ChatRoom room = chatRoomService.getRoom(id);
		//해당 채팅방이 없으면 채팅방 리스트로 되돌아감.
		if(room == null) return "redirect:/chatrooms";
		
		List<ChatMessage> chatMessages = chatMessageRepository.findByRoomIdOrderByTimestampAsc(id);
		
		//룸정보와, 생성자 정보를 전달
		model.addAttribute("room", room);
		model.addAttribute("userName", principal.getName());
		model.addAttribute("chatMessages", chatMessages);  // ✅ 이력 전달
		
		//chat.jsp로 이동 처리
		return "chat"; // chat.jsp
	}
}
