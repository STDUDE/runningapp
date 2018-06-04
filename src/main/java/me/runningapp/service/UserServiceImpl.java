package me.runningapp.service;

import com.google.common.collect.Sets;
import me.runningapp.api.dto.UserDto;
import me.runningapp.api.error.UserAlreadyExistException;
import me.runningapp.model.authority.User;
import me.runningapp.repository.RoleRepository;
import me.runningapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder userPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(userPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(final UserDto userDto) {
        if (usernameExist(userDto.getUsername())) {
            throw new UserAlreadyExistException("There is an account with that username: " + userDto.getUsername());
        }

        final User newUser = new User();

        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(userPasswordEncoder.encode(userDto.getPassword()));
        newUser.setRoles(Sets.newHashSet(roleRepository.findAll()));

        return userRepository.save(newUser);
    }

    private boolean usernameExist(final String username) {
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return userPasswordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(userPasswordEncoder.encode(password));
        userRepository.save(user);
    }
}
