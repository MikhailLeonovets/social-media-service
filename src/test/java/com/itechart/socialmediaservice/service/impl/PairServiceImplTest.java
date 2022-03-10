package com.itechart.socialmediaservice.service.impl;

import com.itechart.socialmediaservice.service.PairCalculatorService;
import com.itechart.socialmediaservice.service.storage.UserStorage;
import com.itechart.socialmediaservice.service.exception.DataInputException;
import com.itechart.socialmediaservice.service.exception.UserNotFoundException;
import com.itechart.socialmediaservice.service.model.Interest;
import com.itechart.socialmediaservice.service.model.User;
import com.itechart.socialmediaservice.service.model.UserPair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class PairServiceImplTest {
	@Mock
	private PairCalculatorService pairCalculatorService;
	@Mock
	private UserStorage userStorage;

	private static final String userName1 = "Misha";
	private static final String userName2 = "Lesha";
	private static final String userName3 = "Artem";

	private static final String interestName1 = "cars";
	private static final String interestName2 = "music";
	private static final String interestName3 = "movies";

	private PairServiceImpl pairService;

	@BeforeEach
	void setUp() {
		pairService = new PairServiceImpl(pairCalculatorService, userStorage);
	}

	@Test
	void testGetPairs() throws DataInputException, UserNotFoundException {
		// Given
		Set<User> users = getUsersSetTest();
		Mockito.doReturn(users).when(userStorage).getUsers();
		Mockito.doReturn(getUserPairsForTest(users)).when(pairCalculatorService).getPairsOfUsers(users);
		Set<UserPair> expectedUserPairs = getUserPairsForTest(users);

		// When
		Set<UserPair> actualUserPairs = pairService.getPairs();

		// Then
		Assertions.assertIterableEquals(expectedUserPairs, actualUserPairs);
	}

	Set<UserPair> getUserPairsForTest(Set<User> users) {
		Interest interest1 = new Interest(interestName1);
		Interest interest2 = new Interest(interestName2);
		User firstUser = users.stream()
				.filter(user -> user.getName().equals(userName1))
				.findFirst()
				.get();
		User secondUser = users.stream()
				.filter(user -> user.getName().equals(userName2))
				.findFirst()
				.get();
		UserPair userPair = new UserPair(firstUser, secondUser, Set.of(interest1, interest2));
		return Set.of(userPair);
	}

	/**
	 * @return set of users for testing
	 */
	Set<User> getUsersSetTest() {
		Interest interest1 = new Interest(interestName1);
		Interest interest2 = new Interest(interestName2);
		Interest interest3 = new Interest(interestName3);
		User user1 = new User(userName1, Set.of(interest1, interest2));
		User user2 = new User(userName2, Set.of(interest1, interest2, interest3));
		User user3 = new User(userName3, Set.of(interest3));
		return Set.of(user1, user2, user3);
	}
}