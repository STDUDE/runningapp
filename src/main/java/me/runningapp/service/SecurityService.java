package me.runningapp.service;

import me.runningapp.model.authority.Role;
import me.runningapp.model.authority.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
    OAuth2AccessToken generateOAuth2AccessToken(User user, List<Role> roles, List<String> scopes);

}
