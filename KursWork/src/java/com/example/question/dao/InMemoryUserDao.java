package com.example.question.dao;

import com.example.question.model.User;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDao implements UserDao {

    private final Map<Integer, User> users = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(User user) {
        user.setId(currentId++);
        users.put(user.getId(), user);
    }

    @Override
    public User findById(Integer id) {
        return users.get(id);
    }

    @Override
    public User findByEmail(String email) {
        return users.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void deleteById(Integer id) {
        users.remove(id);
    }
}
