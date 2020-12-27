package com.spring.files.springbootfileassignment.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.files.springbootfileassignment.exception.CustomFileException;
import com.spring.files.springbootfileassignment.utils.ErrorMessage;

/**
 * FileUploadValidator class is to perform validation on file object and data
 * 
 * @author vaibhav-kh
 *
 */

@Component
public class FileUploadValidator {

	private static final String FILE_TYPE = "text/plain";

	private static enum HEADER {
		PRIMARY_KEY, NAME, DESCRIPTION, UPDATED_TIMESTAMP
	};

	public boolean validate(MultipartFile file) throws IOException {

		boolean hasError = false;

		if (file.isEmpty()) {
			hasError = true;
			throw new CustomFileException(HttpStatus.NO_CONTENT, ErrorMessage.EMPTY_FILE);
		}
		if (!FILE_TYPE.equalsIgnoreCase(file.getContentType())) {
			hasError = true;
			throw new CustomFileException(HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessage.INVALID_FORMAT);
		} else {
			validateFileContent(file);
		}

		return hasError;
	}

	private boolean validateFileContent(MultipartFile file) throws IOException {
		boolean hasError = false;
		try (InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
				BufferedReader reader = new BufferedReader(inputStreamReader)) {
			String line = reader.readLine();
			String[] headerData = line.split(",");
			if (headerData[0].equals(HEADER.PRIMARY_KEY.toString()) && headerData[1].equals(HEADER.NAME.toString())
					&& headerData[2].equals(HEADER.DESCRIPTION.toString())
					&& headerData[3].equals(HEADER.UPDATED_TIMESTAMP.toString())) {
				while (line != null) {
					line = reader.readLine();
					if (line != null) {
						String[] data = line.split(",");
						if (data.length != HEADER.values().length) {
							hasError = true;
							throw new CustomFileException(HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessage.INCOMPLETE_RECORD);
						} else {
							if (StringUtils.isEmpty(data[0])) {
								hasError = true;
								throw new CustomFileException(HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessage.BLANK_PRIMARY_KEY);
							}
						}
					}
				}
			} else {
				hasError = true;
				throw new CustomFileException(HttpStatus.UNPROCESSABLE_ENTITY, ErrorMessage.MISSING_HEADER);
			}

		}
		return hasError;
	}

}
