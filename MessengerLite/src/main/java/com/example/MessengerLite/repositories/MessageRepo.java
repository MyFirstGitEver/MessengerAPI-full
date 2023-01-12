package com.example.MessengerLite.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MessengerLite.entities.MessageEntity;

public interface MessageRepo extends JpaRepository<MessageEntity, Integer>
{
	@Query("Select m from message m Where (fromId = :user AND toId = :other) OR"
			+ " (fromId = :other AND toId = :user) order by sentDate desc, id desc")
	public Page<MessageEntity> getLasMessages(int user, int other, Pageable pager);
	
	@Query("Select m from message m Where ((fromId = :user AND toId = :other) OR"
			+ " (fromId = :other AND toId = :user)) AND type = 1")
	public List<MessageEntity> getAllPictureMsgIds(int user, int other);
}