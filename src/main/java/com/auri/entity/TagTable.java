package com.auri.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TagTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tagID;
	private String imageID;
	private String tagColor;
	private String tagName;
	
	
	public int getTagID() {
		return tagID;
	}
	public void setTagID(int tagID) {
		this.tagID = tagID;
	}
	public String getTagColor() {
		return tagColor;
	}
	public void setTagColor(String tagColor) {
		this.tagColor = tagColor;
	}
	public String getImageID() {
		return imageID;
	}
	public void setImageID(String imageID) {
		this.imageID = imageID;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
}
