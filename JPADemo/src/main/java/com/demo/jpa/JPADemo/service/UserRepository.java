package com.demo.jpa.JPADemo.service;

import com.demo.jpa.JPADemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
