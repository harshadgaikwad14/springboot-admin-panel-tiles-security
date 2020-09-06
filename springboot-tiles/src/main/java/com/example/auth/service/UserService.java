package com.example.auth.service;

import java.util.List;

import com.example.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

	List<User> findAll();

	User findById(long id);

	void deleteById(long id);
}
