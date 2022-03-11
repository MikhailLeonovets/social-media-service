package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.GraphAlgorithmService;
import com.itechart.socialmediaservice.service.converter.GraphConverter;
import com.itechart.socialmediaservice.service.PairCalculatorService;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.UserPair;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.graph.Graph;
import com.itechart.socialmediaservice.service.model.graph.Vertex;
import com.itechart.socialmediaservice.service.model.graph.VertexPair;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PairGraphCalculatorServiceImpl implements PairCalculatorService {
	private final GraphConverter graphService;
	private final GraphAlgorithmService graphAlgorithmService;

	public PairGraphCalculatorServiceImpl(GraphConverter graphService, GraphAlgorithmService graphAlgorithmService) {
		this.graphService = graphService;
		this.graphAlgorithmService = graphAlgorithmService;
	}

	/**
	 * @return pairs from incoming users. It calculates by interception of users' interests.
	 */
	@Override
	public Set<UserPair> getPairsOfUsers(Set<User> users) throws DataInputException {
		if (users == null || users.isEmpty()) {
			throw new DataInputException();
		}
		Set<UserPair> userPairs = new HashSet<>();
		Graph usersGraph = graphService.convertToGraph(users);
		Set<VertexPair> vertexPairs = graphAlgorithmService.findVerticesPairsByWeightiestEdge(usersGraph);
		for (VertexPair vertexPair : vertexPairs) {
			User firstUser = getUserFromVertex(users, vertexPair.getFirstVertex());
			User secondUser = getUserFromVertex(users, vertexPair.getSecondVertex());
			Set<Interest> interestIntersection = firstUser.getInterests().stream()
					.filter(secondUser.getInterests()::contains)
					.collect(Collectors.toSet());
			userPairs.add(new UserPair(firstUser, secondUser, interestIntersection));
		}
		return userPairs;
	}

	private User getUserFromVertex(Set<User> users, Vertex vertex) {
		return users.stream()
				.filter(user -> user.getUserName().equals(vertex.getLabel()))
				.findFirst()
				.get();
	}
}
