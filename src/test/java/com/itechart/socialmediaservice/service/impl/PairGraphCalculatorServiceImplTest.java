package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.GraphAlgorithmService;
import com.itechart.socialmediaservice.service.converter.GraphConverter;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.UserPair;
import com.itechart.socialmediaservice.service.model.graph.Edge;
import com.itechart.socialmediaservice.service.model.graph.Graph;
import com.itechart.socialmediaservice.service.model.graph.Vertex;
import com.itechart.socialmediaservice.service.model.graph.VertexPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PairGraphCalculatorServiceImplTest {
	@Mock
	private GraphConverter graphService;
	@Mock
	private GraphAlgorithmService graphAlgorithmService;

	private static final String userName1 = "Misha";
	private static final String userName2 = "Lesha";
	private static final String userName3 = "Artem";

	private static final String interestName1 = "cars";
	private static final String interestName2 = "music";
	private static final String interestName3 = "movies";

	private PairGraphCalculatorServiceImpl pairGraphCalculatorService;

	@BeforeEach
	void setUp() {
		pairGraphCalculatorService = new PairGraphCalculatorServiceImpl(graphService, graphAlgorithmService);
	}

	@Test
	void testGetPairsOfUsers() throws DataInputException {
		// Given
		Set<User> users = getUsersSetTest();
		Graph usersGraph = getUsersGraphTest();
		Mockito.doReturn(usersGraph).when(graphService).convertToGraph(users);
		Set<VertexPair> vertexPairs = getVertexPairForTest();
		Mockito.doReturn(vertexPairs).when(graphAlgorithmService).findVerticesPairsByWeightiestEdge(usersGraph);
		Set<UserPair> expectedResult = getUserPairsForTest(users);

		// When
		Set<UserPair> actualResult = pairGraphCalculatorService.getPairsOfUsers(users);

		// Then
		Assertions.assertIterableEquals(expectedResult, actualResult);
	}

	@Test
	void testGetPairsOfUsersThrowsDataInputExceptionBecauseNullData() {
		// Given
		// When
		// Then
		Assertions.assertThrows(DataInputException.class, () -> pairGraphCalculatorService.getPairsOfUsers(null));
	}

	@Test
	void testGetPairsOfUsersThrowsDataInputExceptionBecauseEmptyData() {
		// Given
		// When
		// Then
		Assertions.assertThrows(DataInputException.class,
				() -> pairGraphCalculatorService.getPairsOfUsers(new HashSet<>()));
	}

	Set<UserPair> getUserPairsForTest(Set<User> users) {
		Interest interest1 = new Interest(interestName1);
		Interest interest2 = new Interest(interestName2);
		User firstUser = users.stream()
				.filter(user -> user.getUserName().equals(userName1))
				.findFirst()
				.get();
		User secondUser = users.stream()
				.filter(user -> user.getUserName().equals(userName2))
				.findFirst()
				.get();
		UserPair userPair = new UserPair(firstUser, secondUser, Set.of(interest1, interest2));
		return Set.of(userPair);
	}

	/**
	 * @return set of users for testing
	 */
	Set<User> getUsersSetTest() {
		Interest interest1 = new Interest(interestName1);
		Interest interest2 = new Interest(interestName2);
		Interest interest3 = new Interest(interestName3);
		User user1 = new User(userName1, Set.of(interest1, interest2));
		User user2 = new User(userName2, Set.of(interest1, interest2, interest3));
		User user3 = new User(userName3, Set.of(interest3));
		return Set.of(user1, user2, user3);
	}


	Graph getUsersGraphTest() {
		Vertex vertex1 = new Vertex(userName1);
		Vertex vertex2 = new Vertex(userName2);
		Vertex vertex3 = new Vertex(userName3);
		Edge edge1_2 = new Edge(vertex2, 2);
		Edge edge2_1 = new Edge(vertex1, 2);
		Edge edge2_3 = new Edge(vertex3, 1);
		Edge edge3_2 = new Edge(vertex2, 1);
		vertex1.setEdges(Set.of(edge1_2));
		vertex2.setEdges(Set.of(edge2_1, edge2_3));
		vertex3.setEdges(Set.of(edge3_2));
		return new Graph(Set.of(vertex1, vertex2, vertex3));
	}

	Set<VertexPair> getVertexPairForTest() {
		Vertex vertex1 = new Vertex(userName1);
		Vertex vertex2 = new Vertex(userName2);
		Edge edge1_2 = new Edge(vertex2, 2);
		Edge edge2_1 = new Edge(vertex1, 2);
		vertex1.setEdges(Set.of(edge1_2));
		vertex2.setEdges(Set.of(edge2_1));
		return Set.of(new VertexPair(vertex1, vertex2));
	}
}