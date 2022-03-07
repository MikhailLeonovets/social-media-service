package com.itechart.socialmediaservice.service.converter.impl;

import com.itechart.socialmediaservice.service.converter.GraphConverter;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.graph.Edge;
import com.itechart.socialmediaservice.service.model.graph.Graph;
import com.itechart.socialmediaservice.service.model.graph.Vertex;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserGraphConverter implements GraphConverter {

	@Override
	public Graph convertToGraph(Set<User> users) throws DataInputException {
		if (users == null) {
			throw new DataInputException();
		}
		Graph graph = new Graph();
		Set<Vertex> vertices = convertUsersToVerticesWithEmptyEdges(users);
		for (User user : users) {
			addPossibleEdgesToUserAsVertex(users, vertices, user);
		}
		graph.addVertices(vertices);
		return graph;
	}

	private void addPossibleEdgesToUserAsVertex(Set<User> users, Set<Vertex> vertices, User user) {
		Set<User> adjacentUsers = new HashSet<>(users);
		adjacentUsers.remove(user);
		Vertex userAsVertex = vertices.stream()
				.filter(vertex -> vertex.getLabel().equals(user.getName()))
				.findFirst()
				.get();
		for (User adjacentUser : adjacentUsers) {
			int edgeWeight = (int) user.getInterests().stream()
					.filter(adjacentUser.getInterests()::contains).count();
			if (edgeWeight != 0) {
				Edge edge = new Edge();
				edge.setTo(vertices.stream()
						.filter(vertex -> vertex.getLabel().equals(adjacentUser.getName()))
						.findFirst().get());
				edge.setWeight(edgeWeight);
				userAsVertex.addEdge(edge);
			}
		}
	}

	private Set<Vertex> convertUsersToVerticesWithEmptyEdges(Set<User> users) {
		Set<Vertex> vertices = new HashSet<>();
		for (User user : users) {
			Vertex vertex = new Vertex(user.getName());
			vertices.add(vertex);
		}
		return vertices;
	}
}
