package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class XlsxUserParser implements UserParser {
	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException {
		Set<User> users = new HashSet<>();
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(convert(file));
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
		for (int i = 1; i <= xssfSheet.getLastRowNum(); i++) {
			Row row = xssfSheet.getRow(i);
			User user = new User(row.getCell(0).getStringCellValue());
			Set<Interest> interests = Stream.of(row.getCell(1).getStringCellValue().split(","))
					.map(item -> new Interest(item.trim().toLowerCase()))
					.collect(Collectors.toSet());
			user.addInterests(interests);
			users.add(user);
		}
		return users;
	}

	private FileInputStream convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return new FileInputStream(convFile);
	}
}
