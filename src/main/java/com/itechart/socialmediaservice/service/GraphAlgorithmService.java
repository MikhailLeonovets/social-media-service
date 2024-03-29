package com.itechart.socialmediaservice.service;

import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.model.graph.Graph;
import com.itechart.socialmediaservice.service.model.graph.VertexPair;

import java.util.Set;

public interface GraphAlgorithmService {

	Set<VertexPair> findVerticesPairsByWeightiestEdge(Graph graph) throws DataInputException;

}
