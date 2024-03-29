package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.exception.UserNotFoundException;

public enum UserParserStorage {
	CSV_USER_PARSER(new CsvUserParser(), "csv"),
	JSON_USER_PARSER(new JsonUserParser(), "json"),
	XLSX_USER_PARSER(new XlsxUserParser(), "xlsx"),
	XML_USER_PARSER(new XmlStaxUserParser(), "xml");

	private final UserParser userParser;
	private final String fileExtension;

	UserParserStorage(UserParser userParser, String fileExtension) {
		this.userParser = userParser;
		this.fileExtension = fileExtension;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public static UserParser getUserParserByFileExtension(String fileExtension) throws UserNotFoundException {
		return getUserParserEnumByFileExtension(fileExtension).userParser;
	}

	public static UserParserStorage getUserParserEnumByFileExtension(String fileExtension)
			throws UserNotFoundException {
		for (UserParserStorage userParserStorage : UserParserStorage.values()) {
			if (userParserStorage.getFileExtension().equals(fileExtension)) {
				return userParserStorage;
			}
		}
		throw new UserNotFoundException();
	}
}
