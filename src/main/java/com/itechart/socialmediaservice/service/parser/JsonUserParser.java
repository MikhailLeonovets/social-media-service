package com.itechart.socialmediaservice.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.socialmediaservice.service.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public class JsonUserParser implements UserParser {
	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(file.getBytes(),
				objectMapper.getTypeFactory().constructCollectionType(Set.class, User.class));
	}
}
