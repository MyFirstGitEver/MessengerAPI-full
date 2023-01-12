package com.example.MessengerLite.dtos;

import com.example.MessengerLite.entities.MessageEntity;

public class PictureMessageDTO extends MessageDTO
{
    private int width, height;

    public PictureMessageDTO(boolean isMyUser, MessageEntity message, int width, int height)
    {
        super(isMyUser, message);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}