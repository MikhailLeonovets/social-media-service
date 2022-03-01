package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.graph.Graph;

import java.util.Set;

public interface GraphService {

	Graph convertUsersToGraph(Set<User> users) throws DataInputException;

}
