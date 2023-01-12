package com.example.MessengerLite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MessengerLite.entities.WebLinkEntity;

public interface WebLinkRepo extends JpaRepository<WebLinkEntity, Integer>
{
	@Query("SELECT l from weblink l Where ownerId = :id")
	public List<WebLinkEntity> getAllLinksByOwnerId(int id);
}