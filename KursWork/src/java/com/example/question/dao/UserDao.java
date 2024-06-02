package com.example.question.dao;

import com.example.question.model.User;
import java.util.Collection;

public interface UserDao {
    void create(User user);
    User findById(Integer id);
    User findByEmail(String email);
    Collection<User> findAll();
    void update(User user);
    void deleteById(Integer id);
}
