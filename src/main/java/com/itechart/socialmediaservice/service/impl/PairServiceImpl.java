package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.GraphService;
import com.itechart.socialmediaservice.service.PairService;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.Pair;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.graph.Edge;
import com.itechart.socialmediaservice.service.model.graph.Graph;
import com.itechart.socialmediaservice.service.model.graph.Vertex;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PairServiceImpl implements PairService {
	private final GraphService graphService;

	public PairServiceImpl(GraphService graphService) {
		this.graphService = graphService;
	}

	/**
	 * @return pairs from incoming users. It calculates by interception of users' interests.
	 */
	@Override
	public Set<Pair> getPairsOfUsers(Set<User> users) throws DataInputException {
		Set<Pair> pairs = new HashSet<>();
		Graph usersGraph = graphService.convertUsersToGraph(users);
		for (Vertex vertex : usersGraph.getVertices()) {
			Edge weightiestEdgeCurrentVertex = findWeightiestEdge(vertex);
			Vertex adjacentVertex = weightiestEdgeCurrentVertex.getTo();
			if (findWeightiestEdge(adjacentVertex).getTo().equals(vertex)) {
				pairs.add(createPairFromVertices(users, vertex, adjacentVertex));
				usersGraph.getVertices().remove(vertex);
				usersGraph.getVertices().remove(adjacentVertex);
			}
		}
		return pairs;
	}

	private Edge findWeightiestEdge(Vertex vertex) {
		return vertex.getEdges().stream()
				.max(Comparator.comparing(Edge::getWeight))
				.get();
	}

	private Pair createPairFromVertices(Set<User> users, Vertex firstVertex, Vertex secondVertex) {
		User firstUser = getUserFromVertex(users, firstVertex);
		User secondUser = getUserFromVertex(users, secondVertex);
		Set<Interest> interestsIntersection = firstUser.getInterests().stream()
				.filter(secondUser.getInterests()::contains)
				.collect(Collectors.toSet());
		return new Pair(firstUser, secondUser, interestsIntersection);
	}

	private User getUserFromVertex(Set<User> users, Vertex vertex) {
		return users.stream()
				.filter(user -> user.getName().equals(vertex.getLabel()))
				.findFirst()
				.get();
	}

}
