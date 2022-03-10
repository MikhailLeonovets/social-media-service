package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XlsxUserParser implements UserParser {
	/**
	 *
	 * @param file - xlsx contains users
	 * @return Set of users
	 * @throws IOException
	 */
	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException {
		Set<User> users = new HashSet<>();
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(convert(file));
		int sheetNumber = 0;
		int startUserInfoRow = 1;
		int cellUserNameNumber = 0;
		int cellInterestsNumber = 1;
		XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetNumber);
		for (int i = startUserInfoRow; i <= xssfSheet.getLastRowNum(); i++) {
			Row row = xssfSheet.getRow(i);
			User user = new User(row.getCell(cellUserNameNumber).getStringCellValue());
			Set<Interest> interests = Stream.of(row.getCell(cellInterestsNumber).getStringCellValue().split(","))
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
