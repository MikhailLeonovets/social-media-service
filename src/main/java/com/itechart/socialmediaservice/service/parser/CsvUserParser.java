package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.User;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

@Component
public class CsvUserParser implements UserParser {
	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException {
		Reader reader = new InputStreamReader(file.getInputStream());
		CsvToBeanBuilder<User> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
		csvToBeanBuilder.withType(User.class);
		int headerLineNumber = 1;
		csvToBeanBuilder.withSkipLines(headerLineNumber);
		CsvToBean<User> csvToBean = csvToBeanBuilder
				.build();
		return new HashSet<>(csvToBean.parse());
	}
}
