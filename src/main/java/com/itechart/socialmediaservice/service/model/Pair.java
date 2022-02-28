package com.itechart.socialmediaservice.service.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Pair implements Serializable {
	private User firstUser;
	private User secondUser;
	private Set<Interest> interestsIntersection;
}
