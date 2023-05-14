package com.example.MessengerLite.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "message")
public class MessageEntity implements Comparable<MessageEntity>
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	private int type, toId, fromId;
	private boolean deleted, forwarded, isRead;
	private String content;
	@JsonFormat(timezone = "GMT+07:00")
	private Date sentDate;
	
	public MessageEntity()
	{
		
	}
	
	public MessageEntity(int type, boolean isRead, String content, Date sentDate) {
		super();
		this.type = type;
		this.isRead = isRead;
		this.content = content;
		this.sentDate = sentDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean getForwarded() {
		return forwarded;
	}

	public void setForwarded(boolean forwarded) {
		this.forwarded = forwarded;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return sentDate;
	}

	public void setDate(Date date) {
		this.sentDate = date;
	}

	public boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public int compareTo(MessageEntity message) 
	{
		return -sentDate.compareTo(message.getDate());
	}
}