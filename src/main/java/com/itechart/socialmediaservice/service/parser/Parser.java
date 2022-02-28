package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Parser {
	List<User> convertToUsers(File file) throws IOException;
}
