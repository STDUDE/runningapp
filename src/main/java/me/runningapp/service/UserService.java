package me.runningapp.service;

import me.runningapp.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
