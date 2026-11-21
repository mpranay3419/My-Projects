package com.coforge.ars.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.ars.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
