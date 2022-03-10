package com.itechart.socialmediaservice.controller;

import com.itechart.socialmediaservice.service.PairService;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.UserPair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/social-media-service/pairs")
public class PairController {
	private final PairService pairService;

	public PairController(PairService pairService) {
		this.pairService = pairService;
	}

	@GetMapping
	public Set<UserPair> getPairs() throws UserNotFoundException, DataInputException {
		return pairService.getPairs();
	}
}
