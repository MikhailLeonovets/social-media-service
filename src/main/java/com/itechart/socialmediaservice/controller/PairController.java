package com.itechart.socialmediaservice.controller;

import com.itechart.socialmediaservice.service.PairCalculatorService;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/social-media-service/pairs")
public class PairController {
	private final PairCalculatorService pairCalculatorService;

	public PairController(PairCalculatorService pairService) {
		this.pairCalculatorService = pairService;
	}

	@GetMapping
	public Set<Pair> getPairs() throws UserNotFoundException, DataInputException { //TODO handle exception
		return pairCalculatorService.getPairs();
	}
}
