package com.example.MessengerLite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MessengerLite.entities.WebLinkEntity;
import com.example.MessengerLite.repositories.WebLinkRepo;

@RestController
@RequestMapping("/media/")
public class MediaController
{
	@Autowired
	WebLinkRepo webLinkRepo;
	
	@GetMapping("/getLinks/{id}")
	public ResponseEntity<List<WebLinkEntity>> getLinks(@PathVariable int id)
	{
		List<WebLinkEntity> links = webLinkRepo.getAllLinksByOwnerId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(links);
	}
	
	@PostMapping("/editLink/{action}")
	public ResponseEntity<Integer> editLink(
			@RequestBody WebLinkEntity webLink,
			@PathVariable int action)
	{	
		if(action == 0)
		{
			int id = webLinkRepo.save(webLink).getId();
			return ResponseEntity.status(HttpStatus.OK).body(id);
		}
		else
		{
			webLinkRepo.delete(webLink);
			return ResponseEntity.status(HttpStatus.OK).body(webLink.getId());
		}
	}
}
