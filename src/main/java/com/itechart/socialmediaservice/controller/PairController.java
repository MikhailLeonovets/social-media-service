package com.itechart.socialmediaservice.controller;

import com.itechart.socialmediaservice.service.PairService;
import com.itechart.socialmediaservice.service.model.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/social-media-service/pairs")
public class PairController {
	private final PairService pairService;

	public PairController(PairService pairService) {
		this.pairService = pairService;
	}

	@GetMapping
	public List<Pair> getPairs() {
		//return pairService.getPairsOfUsers();
		return null;
	}
}
