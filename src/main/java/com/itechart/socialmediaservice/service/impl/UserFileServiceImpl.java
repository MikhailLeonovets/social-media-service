package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.UserFileService;
import com.itechart.socialmediaservice.service.cache.UserCache;
import com.itechart.socialmediaservice.service.exception.FileUploadException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.parser.JsonUserParser;
import com.itechart.socialmediaservice.service.parser.XmlUserParser;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class UserFileServiceImpl implements UserFileService {
	private final JsonUserParser jsonParser;
	private final XmlUserParser xmlParser;
	private final UserCache userCache;

	public UserFileServiceImpl(JsonUserParser jsonParser, XmlUserParser xmlParser, UserCache userCache) {
		this.jsonParser = jsonParser;
		this.xmlParser = xmlParser;
		this.userCache = userCache;
	}

	@Override
	public void createUsersFromFile(MultipartFile file) throws IOException, UserNotFoundException, FileUploadException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if (extension == null) {
			throw new FileUploadException();
		}
		Set<User> users;
		switch (extension) {
			case "json":
				users = jsonParser.convertToUsers(file);
				break;
			case "xml":
				users = xmlParser.convertToUsers(file);
				break;
			default:
				throw new FileUploadException();
		}
		if (users.isEmpty()) {
			throw new UserNotFoundException();
		}
		userCache.setUsers(users);
	}

}
