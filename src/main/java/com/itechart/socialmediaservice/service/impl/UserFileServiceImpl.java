package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.UserFileService;
import com.itechart.socialmediaservice.service.cache.UserCache;
import com.itechart.socialmediaservice.service.exception.FileUploadException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.parser.CsvUserParser;
import com.itechart.socialmediaservice.service.parser.JsonUserParser;
import com.itechart.socialmediaservice.service.parser.XlsxUserParser;
import com.itechart.socialmediaservice.service.parser.XmlUserParser;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class UserFileServiceImpl implements UserFileService {
	private final JsonUserParser jsonParser;
	private final XmlUserParser xmlParser;
	private final CsvUserParser csvUserParser;
	private final XlsxUserParser xlsxUserParser;
	private final UserCache userCache;

	public UserFileServiceImpl(JsonUserParser jsonParser, XmlUserParser xmlParser,
	                           CsvUserParser csvUserParser, XlsxUserParser xlsxUserParser, UserCache userCache) {
		this.jsonParser = jsonParser;
		this.xmlParser = xmlParser;
		this.csvUserParser = csvUserParser;
		this.xlsxUserParser = xlsxUserParser;
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
			case "csv":
				users = csvUserParser.convertToUsers(file);
				break;
			case "xlsx":
				users = xlsxUserParser.convertToUsers(file);
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
