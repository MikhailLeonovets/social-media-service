package com.itechart.socialmediaservice.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.socialmediaservice.service.model.User;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonParser implements Parser {
	@Override
	public List<User> convertToUsers(File file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(file,
				objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
	}
}
