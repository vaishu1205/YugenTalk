package com.yugentalk.chatbackend.repository;

import com.yugentalk.chatbackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsername(String username); // ✅ Add this
}
