package com.example.auth.service;

import com.example.model.User;

public interface UserService {
	void save(User user);

	User findByUserName(String userName);

	User findByUserId(long userId);

	void deleteById(long id);
}
