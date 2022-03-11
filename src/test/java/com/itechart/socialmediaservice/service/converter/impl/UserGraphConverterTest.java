package com.itechart.socialmediaservice.service.converter.impl;

import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.graph.Edge;
import com.itechart.socialmediaservice.service.model.graph.Graph;
import com.itechart.socialmediaservice.service.model.graph.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class UserGraphConverterTest {
	private static final String interestName1 = "music";
	private static final String interestName2 = "cars";
	private static final String interestName3 = "movies";
	private static final String userName1 = "Lesha";
	private static final String userName2 = "Katy";
	private static final int edgeWeight = 2;

	private UserGraphConverter userGraphConverter;

	@BeforeEach
	void setUp() {
		userGraphConverter = new UserGraphConverter();
	}

	@Test
	void testConvertToGraph() throws DataInputException {
		// Given
		Interest interest1 = new Interest(interestName1);
		Interest interest2 = new Interest(interestName2);
		Interest interest3 = new Interest(interestName3);
		User user1 = new User(userName1, Set.of(interest1, interest2, interest3));
		User user2 = new User(userName2, Set.of(interest2, interest1));
		Set<User> users = new HashSet<>(Set.of(user1, user2));

		Graph expectedGraph = new Graph();
		Vertex vertex1 = new Vertex();
		Vertex vertex2 = new Vertex();
		vertex1.setLabel(user1.getUserName());
		vertex2.setLabel(user2.getUserName());

		Edge edge1 = new Edge(vertex2, edgeWeight);
		Edge edge2 = new Edge(vertex1, edgeWeight);

		vertex1.setEdges(Set.of(edge1));
		vertex2.setEdges(Set.of(edge2));

		Set<Vertex> vertices = new HashSet<>(Set.of(vertex1, vertex2));

		expectedGraph.setVertices(vertices);

		// When
		Graph actualGraph = userGraphConverter.convertToGraph(users);

		// Then
		Assertions.assertEquals(expectedGraph, actualGraph);
	}

	@Test
	void testConvertToGraphThrowsDataInputException() {
		// Given
		// When
		// Then
		Assertions.assertThrows(DataInputException.class, () -> userGraphConverter.convertToGraph(null));
	}
}