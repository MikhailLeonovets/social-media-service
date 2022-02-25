package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.repository.entity.User;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

	User save(User user);

	List<User> findAll();

	User findById(Long id) throws UserNotFoundException;

	void deleteById(Long id) throws UserNotFoundException;


}
