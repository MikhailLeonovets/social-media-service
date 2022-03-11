package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.User;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class XmlStaxUserParser implements UserParser {
	private static final String objectUserXmlTag = "user";
	private static final String userNameFieldXmlTag = "userName";
	private static final String interestNameFieldXmlTag = "interestName";

	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException, XMLStreamException {
		Set<User> users = new HashSet<>();
		User user = null;
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(file.getInputStream());
		while (xmlEventReader.hasNext()) {
			XMLEvent xmlEvent = xmlEventReader.nextEvent();
			if (xmlEvent.isStartElement()) {
				StartElement startElement = xmlEvent.asStartElement();
				if (startElement.getName().getLocalPart().equals(objectUserXmlTag)) {
					user = new User();
				} else if (startElement.getName().getLocalPart().equals(userNameFieldXmlTag)) {
					xmlEvent = xmlEventReader.nextEvent();
					user.setUserName(xmlEvent.asCharacters().getData());
				} else if (startElement.getName().getLocalPart().equals(interestNameFieldXmlTag)) {
					xmlEvent = xmlEventReader.nextEvent();
					user.addInterest(new Interest(xmlEvent.asCharacters().getData()));
				} else {
					xmlEvent = xmlEventReader.nextEvent();
				}
			}
			if (xmlEvent.isEndElement()) {
				EndElement endElement = xmlEvent.asEndElement();
				if (endElement.getName().getLocalPart().equals(objectUserXmlTag)) {
					users.add(user);
				}
			}
		}
		return users;
	}
}
