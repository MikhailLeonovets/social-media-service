package com.itechart.socialmediaservice.service.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.socialmediaservice.service.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class JsonParser implements Parser {
	/*@PostConstruct
	public void init() throws JsonProcessingException {
		Interest interest1 = new Interest("cars");
		Interest interest2 = new Interest("movies");
		Interest interest3 = new Interest("music");
		User user1 = new User();
		user1.setName("Misha");
		user1.setInterests(Set.of(interest1, interest2));

		User user2 = new User();
		user2.setName("Lesha");
		user2.setInterests(Set.of(interest3));
		List<User> users = new ArrayList<>(Arrays.asList(user1, user2));
		ObjectMapper objectMapper = new ObjectMapper();

		System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
	}*/

	@Override
	public Set<User> convertToUsers(MultipartFile file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(file.getBytes(),
				objectMapper.getTypeFactory().constructCollectionType(Set.class, User.class));
	}
}
