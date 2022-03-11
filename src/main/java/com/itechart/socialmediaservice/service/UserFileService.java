package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.exception.FileUploadException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

public interface UserFileService {

	void createUsersFromFile(MultipartFile file) throws IOException, UserNotFoundException, FileUploadException, InvalidFormatException, ParserConfigurationException, SAXException, XMLStreamException;

}
