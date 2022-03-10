package com.itechart.socialmediaservice.controller;

import com.itechart.socialmediaservice.service.UserFileService;
import com.itechart.socialmediaservice.service.exception.FileUploadException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@PropertySource("classpath:messages/controller_messages.properties")
@RestController
@RequestMapping("/social-media-service/users")
public class UsersFileController {
	private final UserFileService userFileService;

	@Value("${upload.file.success}")
	private String uploadFileSuccessMsg;

	public UsersFileController(UserFileService userFileService) {
		this.userFileService = userFileService;
	}

	@PostMapping("/upload")
	public ResponseEntity<?> uploadUsersFile(@RequestParam("file") MultipartFile file)
			throws IOException, UserNotFoundException, FileUploadException, InvalidFormatException {
		userFileService.createUsersFromFile(file);
		return ResponseEntity.ok(uploadFileSuccessMsg);
	}
}
