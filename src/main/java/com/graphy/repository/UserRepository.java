package com.graphy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graphy.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
