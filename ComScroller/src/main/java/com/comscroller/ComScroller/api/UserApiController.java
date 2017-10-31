package com.comscroller.ComScroller.api;

import com.comscroller.ComScroller.model.User;
import com.comscroller.ComScroller.service.UserService;
import com.comscroller.ComScroller.service.annotations.Role;
import com.comscroller.ComScroller.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.comscroller.ComScroller.model.User.Role.*;

/**
 * @author Bőhm Balázs
 */
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @Role({USER,ADMIN,MODERATOR})
    @GetMapping
    public ResponseEntity<User> user() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }
}
