package com.itechart.socialmediaservice.service.model.graph;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VertexPair implements Serializable {
	private Vertex firstVertex;
	private Vertex secondVertex;
}
