package com.example.repository;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;

@Service
public class DbInit implements CommandLineRunner {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;

	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) {

		// create a student
		User user1 = new User();
		user1.setUsername("harshad.gaikwad");
		user1.setPassword(passwordEncoder.encode("Admin@123"));

		// create three courses
		Role role1 = new Role();
		role1.setName("ROLE_USER");
		Role role2 = new Role();
		role2.setName("ROLE_ADMIN");

		user1.getRoles().addAll(Arrays.asList(role1, role2));
		role1.getUsers().add(user1);
		role2.getUsers().add(user1);

		userRepository.save(user1);

	}
}
