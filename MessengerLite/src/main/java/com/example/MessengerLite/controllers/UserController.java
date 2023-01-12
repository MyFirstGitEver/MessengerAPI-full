package com.example.MessengerLite.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MessengerLite.dtos.UserNameDTO;
import com.example.MessengerLite.entities.UserEntity;
import com.example.MessengerLite.repositories.UserRepo;

@RestController
@RequestMapping("/user/")
public class UserController 
{
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("avatar/{userName}")
	public ResponseEntity<UserNameDTO> getAvatar(@PathVariable String userName)
	{
		UserEntity user = userRepo.findByName(userName);
		
		if(user == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserNameDTO());
		
		UserNameDTO dto = new UserNameDTO(user.getUserDisplayName(), user.getAvatarPath());
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	@GetMapping("/login/{userName}/{password}")
	public ResponseEntity<UserEntity> login(
			@PathVariable String userName, 
			@PathVariable String password)
	{
		UserEntity user = userRepo.getUserInfo(userName, password);
		
		if(user != null)
			user.setPassword("");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/checkUserName/{userName}")
	public ResponseEntity<Boolean> checkUserName(@PathVariable String userName)
	{
		if(userName.equals(" "))
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(true);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(userRepo.thisUserNameExists(userName) == 1);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Boolean> register(@RequestBody UserEntity newUser)
	{
		userRepo.save(newUser);
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
	
	@PutMapping("/updateInfo")
	public ResponseEntity<Boolean> editUserInformation(@RequestBody UserEntity userInfo)
	{
		Optional<UserEntity> user = userRepo.findById(userInfo.getId());
		
		userInfo.setPassword(user.get().getPassword()); // preserve this information
		userRepo.save(userInfo);
		
		return ResponseEntity.status(HttpStatus.OK).body(true);
	}
}