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
public class Graph implements Serializable {
	private Set<Vertex> vertices;

	public boolean addVertex(Vertex vertex) {
		return vertices.add(vertex);
	}
}
