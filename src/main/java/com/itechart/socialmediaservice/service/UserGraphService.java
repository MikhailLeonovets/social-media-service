package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.graph.Graph;

import java.util.List;

public interface UserGraphService {

	Graph convertUserToGraph(List<User> users);

}
