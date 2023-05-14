package com.example.MessengerLite.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MessengerLite.dtos.ChatBoxDTO;
import com.example.MessengerLite.dtos.MessageDTO;
import com.example.MessengerLite.dtos.PictureDTO;
import com.example.MessengerLite.dtos.PictureMessageDTO;
import com.example.MessengerLite.entities.MessageEntity;
import com.example.MessengerLite.entities.PictureHolder;
import com.example.MessengerLite.entities.UserEntity;
import com.example.MessengerLite.repositories.MessageRepo;
import com.example.MessengerLite.repositories.PictureHolderRepo;
import com.example.MessengerLite.repositories.UserRepo;

@RestController
public class MessageController
{	
	@Autowired
	MessageRepo messageRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PictureHolderRepo pictureHolderRepo;
	
	@Autowired
	SimpMessagingTemplate template;
	
	@MessageMapping("/send")
	public void send(@Payload MessageEntity message)
	{
		template.convertAndSendToUser(message.getToId() + "", "/private", message);
	}
	
	@GetMapping("/getLastMessages/{myUser}")
	public ResponseEntity<List<ChatBoxDTO>> getLastMessage(
			@PathVariable int myUser)
	{
		List<ChatBoxDTO> lastMessages = new ArrayList<>();
		
		List<UserEntity> others = userRepo.getOthers(myUser);

		for(int i=0;i<others.size();i++)
		{
			UserEntity user = others.get(i);
			
			int A = Math.min(myUser, user.getId());
			int B = Math.max(myUser, user.getId());
			
			Pageable pager = PageRequest.of(0, 1);
			Page<MessageEntity> messagePage = messageRepo.getLasMessages(A, B, pager);
			
			if(messagePage.getContent().size() == 0)
			{
				String content = 
						"Gửi lời chào đến " + user.getUserDisplayName() + " đi nào!";
				
				MessageEntity message = new MessageEntity(0, true, content, new Date(0));
				lastMessages.add(new ChatBoxDTO(user, message));
				
				continue;
			}
			
			MessageEntity message = messagePage.getContent().get(0);
			StringBuilder content;
			
			if(message.getFromId() == myUser)
				content = new StringBuilder("You: ");
			else
				content = new StringBuilder(others.get(i).getUserDisplayName() + ": ");
			
			if(message.getType() == MessageDTO.TEXT)
			{
				String fullContent = message.getContent();
				
				if(fullContent.length() <= 30)
					content.append(fullContent);
				else
					content.append(fullContent.substring(0, 30) + "...");
			}
			else if(message.getType() == MessageDTO.PICTURE)
				content.append("Đã gửi một hình ảnh");
			else
				content.append("Đã gửi một like");
			
			message.setContent(content.toString());
			
			lastMessages.add(new ChatBoxDTO(user, message));
		}
		
		Collections.sort(lastMessages);
		
		return ResponseEntity.status(HttpStatus.OK).body(lastMessages);
	}
	
	@GetMapping("getConversations/{myUser}/{theOther}/{pageNumber}")
	public ResponseEntity<List<MessageDTO>> getConversationsAbove(
			@PathVariable("myUser") int myUser, 
			@PathVariable("theOther") int theOther, 
			@PathVariable("pageNumber") int pageNumber)
	{
		Pageable pager = PageRequest.of(pageNumber, 10);
		Page<MessageEntity> messagePage = messageRepo.getLasMessages(
				myUser, theOther, pager);
		
		List<MessageEntity> messages = messagePage.getContent();
		
		for(MessageEntity message : messages)
		{
			if(myUser == message.getToId())
				message.setIsRead(true); // mark some as read if those messages sent to my user
		}
		
		messageRepo.saveAll(messages);
		
		List<MessageEntity> sorted = messages.stream().sorted().toList();
		
		List<MessageDTO> dtos = new ArrayList<>();
		
		for(MessageEntity msg : sorted)
		{
			if(msg.getType() == MessageDTO.PICTURE)
			{
				PictureHolder holder = 
						pictureHolderRepo.getHolderUsingMessageId(msg.getId());
				
				dtos.add(new PictureMessageDTO(msg.getFromId() == myUser, msg, 
						holder.getWidth(), holder.getHeight()));
			}
			else
				dtos.add(new MessageDTO(msg.getFromId() == myUser, msg));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
	@MessageMapping("sendPicture")
	public void sendMessage(@Payload PictureMessageDTO dto)
	{	
		template.convertAndSendToUser(dto.getMessage().getToId() 
				+ "", "/picture", dto);
		
		int id = messageRepo.save(dto.getMessage()).getId();
		pictureHolderRepo.save(new PictureHolder(id, dto.getWidth(), dto.getHeight()));
	}
	
	@MessageMapping("sendMessage")
	public void sendMessage(@Payload MessageEntity message)
	{
		messageRepo.save(message);
		template.convertAndSendToUser(message.getToId() + "", "/text", new MessageDTO(false, message));
	}
	
	@GetMapping("getAllPictures/{user}/{other}")
	public ResponseEntity<List<PictureDTO>> getAllPictures(
			@PathVariable("user") int user,
			@PathVariable("other") int other)
	{
		List<MessageEntity> messages = messageRepo.getAllPictureMsgIds(user, other);
		
		List<PictureDTO> pictures = new ArrayList<>();
		for(MessageEntity message : messages)
		{
			PictureHolder holder =
					pictureHolderRepo.getHolderUsingMessageId(message.getId());
			
			pictures.add(new PictureDTO(
					holder.getWidth(),
					holder.getHeight(),
					message.getContent()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(pictures);
	}
}