package com.example.MessengerLite.dtos;

import com.example.MessengerLite.entities.MessageEntity;
import com.example.MessengerLite.entities.UserEntity;

public class ChatBoxDTO implements Comparable<ChatBoxDTO>
{
	private UserEntity user;
	private MessageEntity lastMessage;
	
	public ChatBoxDTO(UserEntity user, MessageEntity lastMessage) 
	{
		super();
		this.user = user;
		this.lastMessage = lastMessage;
	}

	public UserEntity getUser() {
		return user;
	}



	public void setUser(UserEntity user) {
		this.user = user;
	}



	public MessageEntity getLastMessage() {
		return lastMessage;
	}



	public void setLastMessage(MessageEntity lastMessage) {
		this.lastMessage = lastMessage;
	}



	@Override
	public int compareTo(ChatBoxDTO box) 
	{
		return -lastMessage.getDate().compareTo(box.getLastMessage().getDate());
	}
}