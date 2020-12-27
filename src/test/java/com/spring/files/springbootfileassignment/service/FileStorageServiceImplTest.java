package com.spring.files.springbootfileassignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.files.springbootfileassignment.exception.CustomFileException;
import com.spring.files.springbootfileassignment.model.FileInfo;
import com.spring.files.springbootfileassignment.repository.FileRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileStorageServiceImplTest {
	
	@InjectMocks
	private FilesStorageServiceImpl filesStorageService;
	
	@Mock
	FileRepository fileRepository;
	
	FileInfo fileInfo;
		
	private FileInfo buildFileInfoData(String id, String name, String description, String updatedTimeStamp) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		
		FileInfo fileInfo = new FileInfo();
		fileInfo.setId(id);
		fileInfo.setName(name);
		fileInfo.setDescription(description);
		fileInfo.setUpdatedTimestamp(LocalDateTime.parse(updatedTimeStamp, formatter));
		
		return fileInfo;
	}

	@Test(expected=CustomFileException.class)
	public void findByIdTest_Exception() {
		String primaryKey = "Testing";
		FileInfo fileInfo = buildFileInfoData("rest", "name", "description", "2020-12-10 23:22");
		when(fileRepository.findById("Testing")).thenThrow(CustomFileException.class);
		filesStorageService.findById(primaryKey);
	}
	
	@Test
	public void findByIdTest_Success() {
		String primaryKey = "rest";
		FileInfo fileInfo = buildFileInfoData("rest", "name", "description", "2020-12-10 23:22");
		when(fileRepository.findById("rest")).thenReturn(Optional.of(fileInfo));
		
		FileInfo info = filesStorageService.findById(primaryKey);
		assertEquals(fileInfo, info);
		
	}
}
