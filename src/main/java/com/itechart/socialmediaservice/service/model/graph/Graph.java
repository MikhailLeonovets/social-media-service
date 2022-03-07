package com.itechart.socialmediaservice.service.model.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Graph implements Serializable {
	private Set<Vertex> vertices;

	public Graph() {
		this.vertices = new HashSet<>();
	}

	public boolean addVertex(Vertex vertex) {
		return vertices.add(vertex);
	}

	public void addVertices(Set<Vertex> vertices) {
		this.vertices.addAll(vertices);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Graph graph = (Graph) o;
		return vertices.equals(graph.vertices);
	}

	@Override
	public int hashCode() {
		return Objects.hash(vertices);
	}
}
