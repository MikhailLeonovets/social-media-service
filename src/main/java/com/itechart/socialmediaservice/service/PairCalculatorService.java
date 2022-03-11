package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.UserPair;

import java.util.Set;

public interface PairCalculatorService {

	Set<UserPair> getPairsOfUsers(Set<User> users) throws DataInputException;

}
