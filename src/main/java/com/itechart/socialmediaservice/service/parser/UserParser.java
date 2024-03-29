package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Set;

public interface UserParser {

	Set<User> convertToUsers(MultipartFile file) throws IOException, InvalidFormatException, ParserConfigurationException, SAXException, XMLStreamException;

}
