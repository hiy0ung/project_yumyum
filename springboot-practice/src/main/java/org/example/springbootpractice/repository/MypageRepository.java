package org.example.springbootpractice.repository;

import org.example.springbootpractice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageRepository extends JpaRepository<User, Long> {
}
