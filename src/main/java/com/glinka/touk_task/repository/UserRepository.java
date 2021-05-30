package com.glinka.touk_task.repository;

import com.glinka.touk_task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
