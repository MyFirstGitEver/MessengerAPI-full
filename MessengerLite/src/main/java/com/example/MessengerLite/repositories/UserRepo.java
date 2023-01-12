package com.example.MessengerLite.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.MessengerLite.entities.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer>
{
	@Query("SELECT u from UserEntity u "
			+ "Where userName = :userName AND password = :password")
	public UserEntity getUserInfo(String userName, String password);
	
	@Query("Select u from UserEntity u Where userName = :name")
	public UserEntity findByName(String name);
	
	@Query("SELECT COUNT(*) from UserEntity Where userName = :userName")
	public int thisUserNameExists(String userName);
	
	@Query("SELECT u from UserEntity u Where id != :id")
	public List<UserEntity> getOthers(int id);
}