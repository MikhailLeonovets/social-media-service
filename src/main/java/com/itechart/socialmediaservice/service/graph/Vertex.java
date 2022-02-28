package com.itechart.socialmediaservice.service.graph;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Vertex implements Serializable {
	private String label;
	private Set<Edge> edges;

	public Vertex(String label) {
		this.label = label;
	}

	public boolean addEdge(Edge edge) {
		return edges.add(edge);
	}
}
