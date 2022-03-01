package com.itechart.socialmediaservice.service.model.graph;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
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
}
