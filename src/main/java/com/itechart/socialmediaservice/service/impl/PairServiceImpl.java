package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.PairService;
import com.itechart.socialmediaservice.service.PairCalculatorService;
import com.itechart.socialmediaservice.service.cache.UserCache;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.UserPair;
import com.itechart.socialmediaservice.service.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PairServiceImpl implements PairService {
	private final PairCalculatorService pairService;
	private final UserCache userCache;

	public PairServiceImpl(PairCalculatorService pairService, UserCache userCache) {
		this.pairService = pairService;
		this.userCache = userCache;
	}

	@Override
	public Set<UserPair> getPairs() throws DataInputException, UserNotFoundException {
		Set<User> users = userCache.getUsers();
		if (users.isEmpty()) {
			throw new UserNotFoundException();
		}
		return pairService.getPairsOfUsers(users);
	}
}
