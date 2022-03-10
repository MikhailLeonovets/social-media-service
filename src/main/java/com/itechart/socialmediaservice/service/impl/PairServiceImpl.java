package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.PairService;
import com.itechart.socialmediaservice.service.PairCalculatorService;
import com.itechart.socialmediaservice.service.storage.UserStorage;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.UserPair;
import com.itechart.socialmediaservice.service.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PairServiceImpl implements PairService {
	private final PairCalculatorService pairCalculatorService;
	private final UserStorage userStorage;

	public PairServiceImpl(PairCalculatorService pairService, UserStorage userStorage) {
		this.pairCalculatorService = pairService;
		this.userStorage = userStorage;
	}

	@Override
	public Set<UserPair> getPairs() throws DataInputException, UserNotFoundException {
		Set<User> users = userStorage.getUsers();
		if (users.isEmpty()) {
			throw new UserNotFoundException();
		}
		return pairCalculatorService.getPairsOfUsers(users);
	}
}
