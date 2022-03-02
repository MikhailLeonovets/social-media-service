package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.UserFileService;
import com.itechart.socialmediaservice.service.cache.UserCache;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.parser.JsonParser;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserFileServiceImpl implements UserFileService {
	private final JsonParser jsonParser;
	private final UserCache userCache;

	public UserFileServiceImpl(JsonParser jsonParser, UserCache userCache) {
		this.jsonParser = jsonParser;
		this.userCache = userCache;
	}

	@Override
	public void createUsersFromFile(MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		Set<User> users = new HashSet<>();
		switch (extension) {
			case "json":
				users = jsonParser.convertToUsers(file);
				break;
		}
		userCache.setUsers(users);
	}

}
