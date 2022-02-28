package com.itechart.socialmediaservice.service.cache;

import com.itechart.socialmediaservice.service.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserCache {
	private List<User> users;
}
