package com.itechart.socialmediaservice.service.model.graph;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
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
}
