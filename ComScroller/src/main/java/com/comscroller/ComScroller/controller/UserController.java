package com.comscroller.ComScroller.controller;

import com.comscroller.ComScroller.model.User;
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
        model.addAttribute(new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if (userService.isValid(user)) {
            return redirectToGreeting(user);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user) {
        userService.registration(user);
        return redirectToGreeting(user);
    }
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute(new User());
        return "users";
    }

    @PostMapping("/users")
    public String listUsers(@ModelAttribute User user) {
        if (userService.isAdmin(user) || userService.isModerator(user)) {
            //TODO
        }
        //TODO
        return "users";
    }

    private String redirectToGreeting(@ModelAttribute User user) {
        return "redirect:/user/greet?name=" + user.getUsername();
    }
}
