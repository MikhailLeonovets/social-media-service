package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.model.graph.Edge;
import com.itechart.socialmediaservice.service.model.graph.Graph;
import com.itechart.socialmediaservice.service.model.graph.Vertex;
import com.itechart.socialmediaservice.service.model.graph.VertexPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

class GraphAlgorithmServiceImplTest {
	private static final String vertexLabel1 = "Asdf";
	private static final String vertexLabel2 = "Qwerty";
	private static final String vertexLabel3 = "Fghj";
	private static final int edgeWeight1 = 1;
	private static final int edgeWeight2 = 3;

	private GraphAlgorithmServiceImpl graphAlgorithmService;

	@BeforeEach
	void setUp() {
		graphAlgorithmService = new GraphAlgorithmServiceImpl();
	}

	@Test
	void testFindVerticesPairsByWeightiestEdge() {
		// Given
		Set<Vertex> vertices = getVerticesSet();
		Graph graph = new Graph();
		graph.addVertices(vertices);
		Set<VertexPair> expectedVertexPair = Set.of(new VertexPair(
				vertices.stream()
						.filter(vertex -> vertex.getLabel().equals(vertexLabel2))
						.findFirst()
						.get(),
				vertices.stream()
						.filter(vertex -> vertex.getLabel().equals(vertexLabel1))
						.findFirst()
						.get()
		));

		// When
		Set<VertexPair> actualVertexPair = graphAlgorithmService.findVerticesPairsByWeightiestEdge(graph);

		// Then
		Assertions.assertEquals(expectedVertexPair, actualVertexPair);
	}

	/**
	 * @return List<Vertex> for test graph;
	 */
	private Set<Vertex> getVerticesSet() {
		Vertex vertex1 = new Vertex(vertexLabel1);
		Vertex vertex2 = new Vertex(vertexLabel2);
		Vertex vertex3 = new Vertex(vertexLabel3);
		Edge edge1_2 = new Edge(vertex2, edgeWeight2);
		Edge edge1_3 = new Edge(vertex3, edgeWeight1);
		Edge edge2_1 = new Edge(vertex1, edgeWeight2);
		Edge edge2_3 = new Edge(vertex3, edgeWeight1);
		Edge edge3_1 = new Edge(vertex1, edgeWeight1);
		Edge edge3_2 = new Edge(vertex2, edgeWeight1);
		vertex1.addEdge(edge1_2);
		vertex1.addEdge(edge1_3);
		vertex2.addEdge(edge2_1);
		vertex2.addEdge(edge2_3);
		vertex3.addEdge(edge3_1);
		vertex3.addEdge(edge3_2);

		return Set.of(vertex1, vertex2, vertex3);
	}

}