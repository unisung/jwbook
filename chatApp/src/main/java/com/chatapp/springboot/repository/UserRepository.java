package com.chatapp.springboot.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatapp.springboot.model.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String>{
   Optional<UserEntity> findByUsername(String username);
}
