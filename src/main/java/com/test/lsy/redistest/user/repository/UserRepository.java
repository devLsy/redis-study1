package com.test.lsy.redistest.user.repository;

import com.test.lsy.redistest.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
