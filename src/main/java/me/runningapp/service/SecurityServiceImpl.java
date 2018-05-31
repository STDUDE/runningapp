package me.runningapp.service;

import me.runningapp.model.authority.Role;
import me.runningapp.model.authority.User;
import me.runningapp.utils.server.AuthServerOAuth2Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthorizationServerEndpointsConfiguration configuration;

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autologin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", username));
        }
    }

    @Override
    public OAuth2AccessToken generateOAuth2AccessToken(User user, List<Role> roles, List<String> scopes) {
        Map<String, String> requestParameters = new HashMap<String, String>();
        Map<String, Serializable> extensionProperties = new HashMap<String, Serializable>();

        boolean approved = true;
        Set<String> responseTypes = new HashSet<String>();
        responseTypes.add("code");


        OAuth2Request oauth2Request = new OAuth2Request(requestParameters, "read-write-client", roles, approved, new HashSet<String>(scopes), new HashSet<String>(Arrays.asList("resourceIdTest")), null, responseTypes, extensionProperties);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), "N/A", roles);

        OAuth2Authentication auth = new OAuth2Authentication(oauth2Request, authenticationToken);
        auth.setAuthenticated(true);

        AuthorizationServerTokenServices tokenService = configuration.getEndpointsConfigurer().getTokenServices();

        OAuth2AccessToken token = tokenService.createAccessToken(auth);
        return token;
    }
}
