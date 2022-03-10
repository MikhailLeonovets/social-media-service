package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.UserFileService;
import com.itechart.socialmediaservice.service.parser.UserParser;
import com.itechart.socialmediaservice.service.parser.UserParserStorage;
import com.itechart.socialmediaservice.service.storage.UserStorage;
import com.itechart.socialmediaservice.service.exception.FileUploadException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.User;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class UserFileServiceImpl implements UserFileService {
	private final UserStorage userStorage;

	public UserFileServiceImpl(UserStorage userStorage) {
		this.userStorage = userStorage;
	}

	@Override
	public void createUsersFromFile(MultipartFile file) throws IOException, UserNotFoundException, FileUploadException,
			InvalidFormatException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension == null) {
			throw new FileUploadException();
		}
		UserParser userParser = UserParserStorage.getUserParserByFileExtension(extension);
		Set<User> users = userParser.convertToUsers(file);
		if (users.isEmpty()) {
			throw new UserNotFoundException();
		}
		userStorage.setUsers(users);
	}
}
