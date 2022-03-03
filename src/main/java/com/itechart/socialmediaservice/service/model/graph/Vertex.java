package com.itechart.socialmediaservice.service.model.graph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
public class Vertex implements Serializable {
	private String label;
	private Set<Edge> edges;

	public Vertex() {
		this.edges = new HashSet<>();
	}

	public Vertex(String label) {
		this.label = label;
		this.edges = new HashSet<>();
	}

	public Vertex(String label, Set<Edge> edges) {
		this.label = label;
		this.edges = edges;
	}

	public boolean addEdge(Edge edge) {
		return edges.add(edge);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vertex vertex = (Vertex) o;
		return Objects.equals(label, vertex.label) && Objects.equals(edges, vertex.edges);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label);
	}
}
