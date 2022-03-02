package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.Pair;
import com.itechart.socialmediaservice.service.model.User;

import java.util.Set;

public interface PairService {

	Set<Pair> getPairsOfUsers(Set<User> users) throws DataInputException;

}
