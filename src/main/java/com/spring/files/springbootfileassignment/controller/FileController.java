package com.spring.files.springbootfileassignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.files.springbootfileassignment.message.ResponseMessage;
import com.spring.files.springbootfileassignment.model.FileInfo;
import com.spring.files.springbootfileassignment.service.FilesStorageService;
import com.spring.files.springbootfileassignment.validation.FileUploadValidator;

/**
 * Rest controller for file operations
 */
@RestController
@RequestMapping("/files")
public class FileController {
	
	private static final String UPLOAD_SUCCESSFULL = "Uploaded the file successfully: ";

	@Autowired
	FilesStorageService filesStorageService;

	@Autowired
	FileUploadValidator fileUploadValidator;
	
	/**
	 * uploadFile method is for uploading the plain text file.
	 * @param file
	 * @return
	 * @throws Exception
	 */

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
		String message = "";

		boolean hasError = fileUploadValidator.validate(file);
		if (!hasError) {
			filesStorageService.save(file);
			message = UPLOAD_SUCCESSFULL + file.getOriginalFilename();
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}

	/**
	 * findRecordById method is to find record by primary key
	 * @param primaryKey
	 * @return
	 */
	@GetMapping("/findRecordById/{primaryKey}")
	@ResponseBody
	public ResponseEntity<FileInfo> findRecordById(@PathVariable String primaryKey) {
		FileInfo fileInfo = filesStorageService.findById(primaryKey);
		return ResponseEntity.status(HttpStatus.OK).body(fileInfo);
	}

	/**
	 * deleteRecordById method is for deleting the record by primary key
	 * @param primaryKey
	 * @return
	 */
	@DeleteMapping("/deleteRecordById/{primaryKey}")
	@ResponseBody
	public ResponseEntity<ResponseMessage> deleteRecordById(@PathVariable String primaryKey) {
		String message="";
		filesStorageService.deleteById(primaryKey);
		message = "Record deleted Successfully ";
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	}
}
