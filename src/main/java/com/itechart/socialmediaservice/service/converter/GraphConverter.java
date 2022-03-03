package com.itechart.socialmediaservice.service.converter;

import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.graph.Graph;

import java.util.Set;

public interface GraphConverter {

	Graph convertToGraph(Set<User> users) throws DataInputException;

}
