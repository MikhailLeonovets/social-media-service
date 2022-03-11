package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.User;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class XmlSaxUserParser extends DefaultHandler implements UserParser {
	private static final String objectUserXmlTag = "user";
	private static final String userNameFieldXmlTag = "userName";
	private static final String interestNameFieldXmlTag = "interestName";

	private final Set<User> users;

	private String tmpValue;
	private User userTmp;

	public XmlSaxUserParser(){
		this.users = new HashSet<>();
	}

	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException,
			ParserConfigurationException, SAXException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(file.getInputStream(), this);
		return users;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if (qName.equalsIgnoreCase(objectUserXmlTag)) {
			userTmp = new User();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		tmpValue = new String(ch, start, length);
		tmpValue = tmpValue.replace("\n", "").trim();
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equalsIgnoreCase(objectUserXmlTag)) {
			users.add(userTmp);
		}
		if (qName.equalsIgnoreCase(userNameFieldXmlTag)) {
			userTmp.setUserName(tmpValue);
		}
		if (qName.equalsIgnoreCase(interestNameFieldXmlTag)) {
			userTmp.addInterest(new Interest(tmpValue));
		}
	}
}

