package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.GraphAlgorithmService;
import com.itechart.socialmediaservice.service.model.graph.Edge;
import com.itechart.socialmediaservice.service.model.graph.Graph;
import com.itechart.socialmediaservice.service.model.graph.Vertex;
import com.itechart.socialmediaservice.service.model.graph.VertexPair;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Service
public class GraphAlgorithmServiceImpl implements GraphAlgorithmService {

	@Override
	public Set<VertexPair> findVerticesPairsByWeightiestEdge(Graph graph) {
		Set<VertexPair> vertexPairs = new HashSet<>();
		Set<Vertex> checkedVertices = new HashSet<>();
		for (Vertex vertex : graph.getVertices()) {
			if (checkedVertices.contains(vertex)) {
				continue;
			}
			findVertexPair(vertex, checkedVertices, vertexPairs);
		}
		return vertexPairs;
	}

	private Edge findWeightiestEdge(Vertex vertex) {
		return vertex.getEdges().stream()
				.max(Comparator.comparing(Edge::getWeight))
				.get();
	}

	private void findVertexPair(Vertex vertex, Set<Vertex> checkedVertices, Set<VertexPair> vertexPairs) {
		Edge weightiestEdgeCurrentVertex = findWeightiestEdge(vertex);
		Vertex adjacentVertex = weightiestEdgeCurrentVertex.getTo();
		if (findWeightiestEdge(adjacentVertex).getTo().equals(vertex)) {
			checkedVertices.addAll(Set.of(vertex, adjacentVertex));
			vertexPairs.add(new VertexPair(vertex, adjacentVertex));
		} else {
			vertex.getEdges().remove(weightiestEdgeCurrentVertex);
			findVertexPair(vertex, checkedVertices, vertexPairs);
		}
	}
}
