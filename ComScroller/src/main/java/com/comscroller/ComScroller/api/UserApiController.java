package com.comscroller.ComScroller.api;

import com.comscroller.ComScroller.model.Users;
import com.comscroller.ComScroller.service.UserService;
import com.comscroller.ComScroller.service.annotations.Role;
import com.comscroller.ComScroller.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import org.springframework.dao.DataIntegrityViolationException;

import static com.comscroller.ComScroller.model.Users.Role.*;

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

    @Role({USER, ADMIN, MODERATOR})
    @GetMapping
    public ResponseEntity<Users> user() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.badRequest().build();
    }

    @Role({ADMIN, MODERATOR})
    @GetMapping("/users")
    public ResponseEntity<Iterable<Users>> listAllUser() {
        Users staff = userService.getUser();
        if (userService.isAdmin(staff) || userService.isModerator(staff)) {
            Iterable<Users> users = userService.users();
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.badRequest().build();
    }

    @Role({ADMIN, MODERATOR})
    @GetMapping("/{username}")
    public ResponseEntity<Users> seeUser(@PathVariable String username) {
        Users staff = userService.getUser();
        if (userService.isAdmin(staff) || userService.isModerator(staff)) {
            return ResponseEntity.ok(userService.getUser(username));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users user) {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException ev) {
            return ResponseEntity.badRequest().build();
        } catch (UserIsBannedException eb) {
            return ResponseEntity.badRequest().build();
        }

    }

    @Role({ADMIN})
    @PutMapping("/role")
    public ResponseEntity<Users> changeRole(@RequestBody Users user, Users.Role role) {
        Users staff = userService.getUser();
        if (userService.isAdmin(staff)) {
            return ResponseEntity.ok(userService.changeRole(user, role));
        }
        return ResponseEntity.badRequest().build();
    }

    @Role({ADMIN, MODERATOR})
    @PutMapping("/ban")
    public ResponseEntity<Users> ban(@RequestBody Users user) {
        Users staff = userService.getUser();
        if (userService.isAdmin(staff) || userService.isModerator(staff)) {
            return ResponseEntity.ok(userService.ban(user));
        }
        return ResponseEntity.badRequest().build();
    }

    @Role({ADMIN})
    @PutMapping("/delete")
    public void delete(@RequestBody Users user) {
        Users staff = userService.getUser();
        if (userService.isAdmin(staff)) {
            userService.delete(user);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<Users> registration(@RequestBody Users user) {
        try {
            return ResponseEntity.ok(userService.registration(user));
        } catch (SQLException | DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/logout")
    public ResponseEntity logout(@RequestBody Users user) {
        this.userService.setUser(null);
        return ResponseEntity.ok().build();
    }
}
