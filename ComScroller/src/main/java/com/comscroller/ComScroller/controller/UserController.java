package com.comscroller.ComScroller.controller;

import com.comscroller.ComScroller.model.Users;
import com.comscroller.ComScroller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bőhm Balázs
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/greet")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute(new Users());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Users user, Model model) {
        if (userService.isValid(user)) {
            return redirectToGreeting(user);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }

   /* @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute Users user) {
        userService.registration(user);
        return redirectToGreeting(user);
    }*/
    
    
    @GetMapping("/ban")
    public String ban(Model model) {
        model.addAttribute("user", new Users());
        return "ban";
    }
/*
    @PostMapping("/ban")
    public String ban(@ModelAttribute Users user) {
        userService.registration(user);
        return redirectToGreeting(user);
    }*/
    
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute(new Users());
        return "users";
    }

    @PostMapping("/users")
    public String listUsers(@ModelAttribute Users user) {
        if (userService.isAdmin(user) || userService.isModerator(user)) {
            //TODO
        }
        //TODO
        return "users";
    }

    private String redirectToGreeting(@ModelAttribute Users user) {
        return "redirect:/user/greet?name=" + user.getUsername();
    }
}
