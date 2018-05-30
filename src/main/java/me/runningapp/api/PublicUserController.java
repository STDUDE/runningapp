package me.runningapp.api;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
//import me.runningapp.service.UserAuthenticationService;
import me.runningapp.service.UserRegistrationService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/public/user")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class PublicUserController {
  /*  @NonNull
    UserAuthenticationService authentication;

    @NonNull
    UserRegistrationService registration;

    @PostMapping("/api/register")
    String register(
            final HttpServletRequest request,
            @RequestParam("username") final String username,
            @RequestParam(value = "password", required = false) final String password) {
        registration.register(username, ofNullable(password).equals("") ? null : ofNullable(password) );
        return authentication.login(username, password).orElseThrow(RuntimeException::new);
    }

    @PostMapping("/api/auth")
    String login(
            final HttpServletRequest request,
            @RequestParam("username") final String username,
            @RequestParam("password") final String password) {
        return authentication
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }*/
}
