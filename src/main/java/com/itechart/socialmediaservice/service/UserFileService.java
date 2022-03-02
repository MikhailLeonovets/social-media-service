package com.itechart.socialmediaservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserFileService {

	void createUsersFromFile(MultipartFile file) throws IOException;

}
