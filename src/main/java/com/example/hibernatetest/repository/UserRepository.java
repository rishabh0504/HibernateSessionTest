package com.example.hibernatetest.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hibernatetest.entity.UserEntity;

@Repository

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
