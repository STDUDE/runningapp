package me.runningapp.api;


import me.runningapp.model.Training;
import me.runningapp.model.authority.User;
import me.runningapp.service.TrainingService;
import me.runningapp.service.UserService;
import me.runningapp.utils.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/user/me")
public class UserController {

    @Autowired
    TokenStore tokenStore;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private TrainingService trainingService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public User me(Principal principal) {
        return userService.findByUsername(principal.getName());
    }

/*    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    UserDetails details(Principal principal) {
        return userDetailsService.loadUserByUsername(principal.getName());
    }*/


    @RequestMapping(value = "/extra", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        System.out.println(accessToken);
        return accessToken.getAdditionalInformation();
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    User data(Principal principal) {
        return userService.findByUsername(principal.getName());
    }

    @RequestMapping(value = "/trainings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Training> getAll(Principal principal) {
        return trainingService.getAll(userService.findByUsername(principal.getName()));
    }

/*
    @RequestMapping(value = "/trainings/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(Training training) {
        trainingService.save(training);
        HttpHeaders headers = new HttpHeaders();
        ControllerLinkBuilder linkBuilder = linkTo(methodOn(UserController.class).get(training.getId()));
        headers.setLocation(linkBuilder.toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
*/

    @RequestMapping(value = "/trainings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Training get(@PathVariable Long id, Principal principal) {
//        бред написала
        return trainingService.get(id, userService.findByUsername(principal.getName()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    Training get(@PathVariable Long id) {
        return trainingService.get(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody
    List<Training> get(@RequestParam User user) {
        return trainingService.getAll(user);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Training training) {
        trainingService.update(training);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        trainingService.delete(id);
    }
}
