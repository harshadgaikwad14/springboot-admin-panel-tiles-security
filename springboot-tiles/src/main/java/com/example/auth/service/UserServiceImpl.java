package com.example.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		final Role role = roleRepository.findByName("ROLE_USER");
		if (role != null) {

			user.getRoles().add(role);
			userRepository.save(user);

		}
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findByUserId(long userId) {
		return userRepository.findByUserId(userId);
	}
}
