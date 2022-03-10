package com.itechart.socialmediaservice.service.storage;

import com.itechart.socialmediaservice.service.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Component
@NoArgsConstructor
public class UserStorage {
	private Set<User> users;
	private int version;

	@PostConstruct
	public void init() {
		users = new HashSet<>();
		version = 1;
	}
}
