package com.example.MessengerLite.dtos;

public class UserNameDTO 
{
	private String userDisplayName, avatarPath;
	
	public UserNameDTO()
	{
		
	}

	public UserNameDTO(String userDisplayName, String avatarPath)
	{
		this.userDisplayName = userDisplayName;
		this.avatarPath = avatarPath;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public String getAvatarPath() {
		return avatarPath;
	}	
}
