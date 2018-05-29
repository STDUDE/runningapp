package me.runningapp.service;

import me.runningapp.model.authority.User;

import java.util.Optional;

public interface UserRegistrationService {

    boolean isEnabled();
    User register(String username, Optional<String> password);
}
