package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.PairCalculatorService;
import com.itechart.socialmediaservice.service.PairService;
import com.itechart.socialmediaservice.service.cache.UserCache;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.Pair;
import com.itechart.socialmediaservice.service.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PairCalculatorServiceImpl implements PairCalculatorService {
	private final PairService pairService;
	private final UserCache userCache;

	public PairCalculatorServiceImpl(PairService pairService, UserCache userCache) {
		this.pairService = pairService;
		this.userCache = userCache;
	}

	@Override
	public Set<Pair> getPairs() throws DataInputException, UserNotFoundException {
		Set<User> users = userCache.getUsers();
		if (users.isEmpty()) {
			throw new UserNotFoundException();
		}
		return pairService.getPairsOfUsers(users);
	}
}
