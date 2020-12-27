package com.spring.files.springbootfileassignment.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.spring.files.springbootfileassignment.model.FileInfo;

public interface FilesStorageService {

	public void save(MultipartFile file) throws IOException;
	
	public void deleteById(String primaryKey);

	public FileInfo findById(String primaryKey);

}
