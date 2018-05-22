package me.runningapp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunningController {

    @RequestMapping("/")
    public String index() {
        return "Congratulations from BlogController.java";
    }

}