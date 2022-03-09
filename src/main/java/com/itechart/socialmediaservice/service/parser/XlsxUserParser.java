package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.User;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

@Component
public class XlsxUserParser implements UserParser {
	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException {
		InputStream fileInputStream = new ByteArrayInputStream(file.getBytes());
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

		return null;
	}
}
