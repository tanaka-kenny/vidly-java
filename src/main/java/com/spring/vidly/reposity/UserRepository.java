package com.spring.vidly.reposity;

import com.spring.vidly.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
