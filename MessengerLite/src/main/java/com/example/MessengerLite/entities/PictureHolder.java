package com.example.MessengerLite.entities;

import jakarta.persistence.*;

@Entity(name = "pictureholder")
@Table(name = "picture_holder")
public class PictureHolder
{
	public PictureHolder()
	{
		
	}
	
	public PictureHolder(int messageId, int width, int height) 
	{
		this.messageId = messageId;
		this.width = width;
		this.height = height;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	private int messageId, width, height;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}