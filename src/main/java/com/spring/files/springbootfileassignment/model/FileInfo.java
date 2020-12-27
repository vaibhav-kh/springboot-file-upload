package com.spring.files.springbootfileassignment.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FileInfo {

	@Id
	@Column(name = "PRIMARY_KEY")
	private String id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "UPDATED_TIMESTAMP")
	private LocalDateTime updatedTimestamp;
	
	public FileInfo() {
		super();
	}

	public FileInfo(String id, String name, String description, LocalDateTime updatedTimestamp) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.updatedTimestamp = updatedTimestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(LocalDateTime updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

}
