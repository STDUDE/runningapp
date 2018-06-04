package me.runningapp.api;


import com.google.common.collect.Lists;
import me.runningapp.api.dto.PasswordDto;
import me.runningapp.api.dto.UserDto;
import me.runningapp.api.error.InvalidOldPasswordException;
import me.runningapp.model.authority.User;
import me.runningapp.service.SecurityService;
import me.runningapp.service.UserService;
import me.runningapp.utils.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/public/user")
final class RegistrationController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public OAuth2AccessToken registerUserAccount(@Valid UserDto userDto) {
        LOGGER.debug("Registering user account with information: {}", userDto);

        final User registered = userService.register(userDto);
        OAuth2AccessToken token = securityService.generateOAuth2AccessToken(registered, Lists.newArrayList(registered.getRoles()),  Lists.newArrayList("read", "write"));
        return token;
    }

    // change user password
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse changeUserPassword(@Valid PasswordDto passwordDto) {
        final User user = userService.findByUsername(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse("Password updated successfully");
    }

}
