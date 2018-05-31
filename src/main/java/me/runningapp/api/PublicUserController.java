package me.runningapp.api;


import me.runningapp.api.dto.PasswordDto;
import me.runningapp.api.dto.UserDto;
import me.runningapp.model.authority.User;
import me.runningapp.service.UserService;
import me.runningapp.utils.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Locale;

@RestController
@RequestMapping("/public/user")
final class PublicUserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse registerUserAccount(
            @Valid UserDto userDto, HttpServletRequest request) {
//        LOGGER.debug("Registering user account with information: {}", accountDto);

        final User registered = userService.register(userDto);
//        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));

        return new GenericResponse("success");

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
