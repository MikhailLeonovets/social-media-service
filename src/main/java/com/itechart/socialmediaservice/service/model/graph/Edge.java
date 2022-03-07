package com.itechart.socialmediaservice.service.model.graph;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString(exclude = "to")
@NoArgsConstructor
public class Edge implements Serializable {
	private Vertex to;
	private int weight;

	public Edge(Vertex to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Edge edge = (Edge) o;
		return weight == edge.weight && to.equals(edge.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(to, weight);
	}
}
