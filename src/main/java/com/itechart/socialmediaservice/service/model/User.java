package com.itechart.socialmediaservice.service.model;

import com.itechart.socialmediaservice.service.parser.converter.TextToInterestConverter;
import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class User implements Serializable {
	@CsvBindByPosition(position = 0)
	private String userName;
	@XmlElement(name = "interests")
	@CsvBindAndSplitByPosition(position = 1, splitOn = "\\|", elementType = Interest.class,
			converter = TextToInterestConverter.class)
	private Set<Interest> interests;

	public User() {
		this.interests = new HashSet<>();
	}

	public User(String userName) {
		this.userName = userName;
		this.interests = new HashSet<>();
	}

	public void addInterests(Set<Interest> interests) {
		this.interests.addAll(interests);
	}

	public void addInterest(Interest interest) {
		interests.add(interest);
	}
}
