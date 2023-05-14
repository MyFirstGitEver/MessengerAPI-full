package com.example.MessengerLite.entities;

import jakarta.persistence.*;

@Entity(name = "weblink")
@Table(name = "web_link")
public class WebLinkEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	private String title, link;
	private int ownerId;
	
	public WebLinkEntity()
	{
		
	}
	
	public WebLinkEntity(String title, String link, int ownerId) 
	{
		this.title = title;
		this.link = link;
		this.ownerId = ownerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
}
