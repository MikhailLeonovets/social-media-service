package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.Pair;

import java.util.Set;

public interface PairCalculatorService {

	Set<Pair> getPairs() throws DataInputException, UserNotFoundException;

}
