package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.exception.FileUploadException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserFileService {

	void createUsersFromFile(MultipartFile file) throws IOException, UserNotFoundException, FileUploadException;

}
