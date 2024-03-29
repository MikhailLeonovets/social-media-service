package com.itechart.socialmediaservice.service.parser;

import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.User;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class XmlDomUserParser implements UserParser {
	private static final String TAG_USER_OBJECT = "user";
	private static final String TAG_USER_NAME = "userName";
	private static final String TAG_INTERESTS = "interests";
	private static final String TAG_INTEREST_NAME = "interestName";

	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException {
		Set<User> users = new HashSet<>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			InputStream fileInputStream = file.getInputStream();
			Document document = documentBuilder.parse(fileInputStream);
			document.getDocumentElement().normalize();
			NodeList nodeList = document.getElementsByTagName(TAG_USER_OBJECT);
			for (int i = 0; i < nodeList.getLength(); i++) {
				Set<Interest> interests = new HashSet<>();
				Node node = nodeList.item(i);
				getUsers(users, interests, node);
			}
		} catch (ParserConfigurationException | SAXException e) {
			throw new FileUploadException();
		}
		return users;
	}

	private void getUsers(Set<User> users, Set<Interest> interests, Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			User user = new User();
			Element element = (Element) node;
			user.setUserName(element.getElementsByTagName(TAG_USER_NAME).item(0).getTextContent());
			NodeList interestNodes = ((Element) node).getElementsByTagName(TAG_INTERESTS);
			getInterests(interests, interestNodes);
			user.setInterests(interests);
			users.add(user);
		}
	}

	private void getInterests(Set<Interest> interests, NodeList interestNodes) {
		for (int j = 0; j < interestNodes.getLength(); j++) {
			Node interestNode = interestNodes.item(j);
			if (interestNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element1 = (Element) interestNode;
				Interest interest = new Interest(element1.getElementsByTagName(TAG_INTEREST_NAME)
						.item(0)
						.getTextContent());
				interests.add(interest);
			}
		}
	}
}
