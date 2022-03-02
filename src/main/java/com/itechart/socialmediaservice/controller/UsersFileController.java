package com.itechart.socialmediaservice.controller;

import com.itechart.socialmediaservice.service.UserFileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/social-media-service/users")
public class UsersFileController {
	private final UserFileService userFileService;

	public UsersFileController(UserFileService userFileService) {
		this.userFileService = userFileService;
	}

	@PostMapping("/upload")
	public void createUsersFromFile(@RequestParam("file") MultipartFile file) throws IOException {
		userFileService.createUsersFromFile(file);
	}
}
