package me.runningapp.api;


import com.google.common.collect.Lists;
import me.runningapp.api.dto.PasswordDto;
import me.runningapp.api.dto.UserDto;
import me.runningapp.model.authority.User;
import me.runningapp.service.SecurityService;
import me.runningapp.service.UserService;
import me.runningapp.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.AuthorizationScope;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;
import java.util.Locale;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@RestController
@RequestMapping("/public/user")
final class PublicUserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public OAuth2AccessToken registerUserAccount(
            @Valid UserDto userDto, HttpServletRequest request) {
//        LOGGER.debug("Registering user account with information: {}", accountDto);

        final User registered = userService.register(userDto);
        OAuth2AccessToken token = securityService.generateOAuth2AccessToken(registered, Lists.newArrayList(registered.getRoles()),  Lists.newArrayList("read", "write"));
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return token;

    }

    // change user password
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse changeUserPassword(final Locale locale, @Valid PasswordDto passwordDto) {
        final User user = userService.findByUsername(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
//            throw new InvalidOldPasswordException();
        }
        userService.changeUserPassword(user, passwordDto.getNewPassword());
        return new GenericResponse("Password updated successfully");
    }

}
