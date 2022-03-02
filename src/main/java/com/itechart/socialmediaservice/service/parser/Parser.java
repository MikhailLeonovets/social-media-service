package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface Parser {
	List<User> convertToUsers(MultipartFile file) throws IOException;
}
