package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface UserParser {

	Set<User> convertToUsers(MultipartFile file) throws IOException;

}
