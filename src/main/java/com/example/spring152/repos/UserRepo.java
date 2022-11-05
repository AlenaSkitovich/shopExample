package com.example.spring152.repos;

import com.example.spring152.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
