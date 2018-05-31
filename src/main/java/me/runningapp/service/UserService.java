package me.runningapp.service;

import me.runningapp.api.dto.UserDto;
import me.runningapp.model.authority.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    User register(UserDto userDto);
    boolean checkIfValidOldPassword(User user, String oldPassword);
    void changeUserPassword(User user, String password);
}
