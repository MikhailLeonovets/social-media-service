package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.UserPair;

import java.util.Set;

public interface PairService {

	Set<UserPair> getPairs() throws DataInputException, UserNotFoundException;

}
