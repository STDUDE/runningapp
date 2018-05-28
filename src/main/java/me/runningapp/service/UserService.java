package me.runningapp.service;

import me.runningapp.model.authority.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
