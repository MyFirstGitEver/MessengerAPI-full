package com.example.MessengerLite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.MessengerLite.entities.PictureHolder;

public interface PictureHolderRepo extends JpaRepository<PictureHolder, Integer>
{
	@Query("SELECT h from pictureholder h Where messageId = :id")
	public PictureHolder getHolderUsingMessageId(int id);
}