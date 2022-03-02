package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.UserFileService;
import com.itechart.socialmediaservice.service.parser.JsonParser;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserFileServiceImpl implements UserFileService {
	private final JsonParser jsonParser;

	public UserFileServiceImpl(JsonParser jsonParser) {
		this.jsonParser = jsonParser;
	}

	@Override
	public void createUsersFromFile(MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		switch (extension) {
			case "json": jsonParser.convertToUsers(file);
		}
	}

}
