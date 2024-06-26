package com.example.test.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.entity.Users;

@Repository
public interface RegisterUserRepo extends JpaRepository<Users, Long> {
	Optional<Users> findByUserName(String username);
}
