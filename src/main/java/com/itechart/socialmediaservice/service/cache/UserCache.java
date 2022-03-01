package com.itechart.socialmediaservice.service.cache;

import com.itechart.socialmediaservice.service.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class UserCache {
	private List<User> users;
	private int version;

	public UserCache() {
		this.version = 1;
	}

	public UserCache(List<User> users) {
		this.users = users;
		this.version = 1;
	}

}
