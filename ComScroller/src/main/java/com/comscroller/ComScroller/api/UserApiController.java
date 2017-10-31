package com.comscroller.ComScroller.api;

import com.comscroller.ComScroller.model.User;
import com.comscroller.ComScroller.service.UserService;
import com.comscroller.ComScroller.service.annotations.Role;
import com.comscroller.ComScroller.service.exceptions.*;
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
    
    @Role({ADMIN,MODERATOR})
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> listAllUser() {
        if (userService.isLoggedIn()) {
            Iterable<User> users = userService.users();
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException ev) {
            return ResponseEntity.badRequest().build();
        }  catch (UserIsBannedException eb) {
            return ResponseEntity.badRequest().build();
        }
        
    }
    
    @Role({ADMIN})
    @PutMapping("/role")
    public ResponseEntity<User> changeRole(@RequestBody User user, User.Role role) {
        return ResponseEntity.ok(userService.changeRole(user,role));  
    }
    
    @Role({ADMIN,MODERATOR})
    @PutMapping("/ban")
    public ResponseEntity<User> ban(@RequestBody User user) {
        return ResponseEntity.ok(userService.ban(user));  
    }
    
    
    @Role({ADMIN})
    @PutMapping("/delete")
    public void delete(@RequestBody User user) {
        userService.delete(user);  
    }

    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User user) {
        return ResponseEntity.ok(userService.registration(user));
    }
}
