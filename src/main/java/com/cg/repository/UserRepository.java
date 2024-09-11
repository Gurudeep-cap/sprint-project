package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	public UserEntity getByEmail(String email);
	Optional<UserEntity> findByUsername(String username);

}
