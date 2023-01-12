package com.example.MessengerLite.dtos;

import com.example.MessengerLite.entities.MessageEntity;

public class MessageDTO
{
	public static final int TEXT = 0;
    public static final int PICTURE = 1;
    public static final int LIKE = 2;
    
    public MessageDTO(boolean isMyUser, MessageEntity message) 
    {
		this.isMyUser = isMyUser;
		this.message = message;
	}

	private boolean isMyUser;
    private MessageEntity message;

    public boolean getIsMyUser() {
        return isMyUser;
    }

    public MessageEntity getMessage() {
        return message;
    }

    public void setIsMyUser(boolean myUser) {
        isMyUser = myUser;
    }

    public void setMessage(MessageEntity message) {
        this.message = message;
    }
}