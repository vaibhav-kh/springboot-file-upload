package com.spring.files.springbootfileassignment.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.files.springbootfileassignment.exception.CustomFileException;
import com.spring.files.springbootfileassignment.model.FileInfo;
import com.spring.files.springbootfileassignment.repository.FileRepository;
import com.spring.files.springbootfileassignment.utils.ErrorMessage;

/**
 * FilesStorageServiceImpl class is perform file operation with interaction with
 * JPA repository
 * 
 * @author vaibhav-kh
 *
 */

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

	@Autowired
	FileRepository fileRepository;

	@Override
	public void save(MultipartFile file) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));) {
			FileInfo fileInfo = null;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

			String line = reader.readLine();
			while (line != null) {
				line = reader.readLine();
				if (line != null) {
					String[] data = line.split(",");
					fileInfo = new FileInfo(data[0], data[1], data[2], LocalDateTime.parse(data[3], formatter));
					fileRepository.save(fileInfo);
				}
			}
		}
	}

	@Override
	public FileInfo findById(String primaryKey) {
		FileInfo fileObj = null;
		Optional<FileInfo> optional = fileRepository.findById(primaryKey);
		if (optional.isPresent()) {
			fileObj = optional.get();
		} else {
			throw new CustomFileException(ErrorMessage.PRIMARY_KEY_NOT_FOUND);
		}
		return fileObj;
	}

	@Override
	public void deleteById(String primaryKey) {
		try {
			fileRepository.deleteById(primaryKey);
		} catch (Exception ex) {
			throw new CustomFileException(ex.getMessage());
		}

	}
}
