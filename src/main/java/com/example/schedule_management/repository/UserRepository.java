package com.example.schedule_management.repository;

import com.example.schedule_management.entity.User;
import org.springframework.stereotype.Repository;

public interface UserRepository {

    User saveUser(User User);
}
