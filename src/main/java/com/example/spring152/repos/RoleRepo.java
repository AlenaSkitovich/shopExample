package com.example.spring152.repos;

import com.example.spring152.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleModel, Long> {
}
