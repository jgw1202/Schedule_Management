package com.example.schedule_management.repository;

/*
import com.example.schedule_management.entity.User;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements  UserRepository {
    private final Map<Long, User> userList = new HashMap<>();

    @Override
    public User saveUser(User user) {
        Long userId = userList.isEmpty() ? 1 : Collections.max(userList.keySet()) + 1;
        user.setId(userId);

        userList.put(userId, user);

        return user;
    }
}
 */