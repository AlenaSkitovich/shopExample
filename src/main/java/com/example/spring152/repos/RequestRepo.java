package com.example.spring152.repos;

import com.example.spring152.models.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepo extends JpaRepository<RequestModel, Long> {

}
