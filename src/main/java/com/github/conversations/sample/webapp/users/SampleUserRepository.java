package com.github.conversations.sample.webapp.users;

import com.github.maximilientyc.conversations.domain.User;
import com.github.maximilientyc.conversations.domain.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @maximilientyc on 29/01/2016.
 */
public class SampleUserRepository implements UserRepository {

	private final List<User> users;


	public SampleUserRepository() {
		users = new ArrayList<User>();
		users.add(new User("max", "Maximilien", "tyc"));
		users.add(new User("bob", "Bob", "Marley"));
		users.add(new User("alice", "Alice", "Caglisse"));
	}

	public User get(String userId) {
		for (User user : users) {
			if (user.getUserId().equals(userId)) {
				return user;
			}
		}
		return null;
	}

	public boolean exists(String userId) {
		for (User user : users) {
			if (user.getUserId().equals(userId)) {
				return true;
			}
		}
		return false;
	}
}
