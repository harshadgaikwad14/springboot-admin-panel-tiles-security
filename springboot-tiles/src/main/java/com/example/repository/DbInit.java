package com.example.repository;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.Role;
import com.example.model.User;

@Service
public class DbInit implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(DbInit.class);
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private RoleRepository roleRepository;
	private UserRoleRepository userRoleRepository;

	public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public void run(String... args) {

		logger.info("****** db init initiated ******");

		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(passwordEncoder.encode("admin"));

		Role role1 = new Role();
		role1.setName("ROLE_USER");

		Role role2 = new Role();
		role2.setName("ROLE_ADMIN");

		Role role3 = new Role();
		role3.setName("ROLE_MANAGER");
		user1.getRoles().addAll(Arrays.asList(role1, role2, role3));
		long userId = userRepository.save(user1);
		logger.info("userId : {}", userId);
		logger.info("****** db init ended ******");
	}
}
