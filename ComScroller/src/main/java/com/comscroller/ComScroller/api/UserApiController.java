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

    @Role({USER,ADMIN,MODERATOR})
    @GetMapping
    public ResponseEntity<Users> user() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.badRequest().build();
    }
    
    @Role({ADMIN,MODERATOR})
    @GetMapping("/users")
    public ResponseEntity<Iterable<Users>> listAllUser() {
        if (userService.isLoggedIn()) {
            Iterable<Users> users = userService.users();
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.badRequest().build();
    }
    
    
    @Role({ADMIN,MODERATOR})
    @GetMapping("/{username}")
    public ResponseEntity<Users> seeUser(@PathVariable String username) {
        Users user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users user) {
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
    public ResponseEntity<Users> changeRole(@RequestBody Users user, Users.Role role) {
        return ResponseEntity.ok(userService.changeRole(user,role));  
    }
    
    @Role({ADMIN,MODERATOR})
    @PutMapping("/ban")
    public ResponseEntity<Users> ban(@RequestBody Users user) {
        return ResponseEntity.ok(userService.ban(user));  
    }
    
    
    @Role({ADMIN})
    @PutMapping("/delete")
    public void delete(@RequestBody Users user) {
        userService.delete(user);  
    }

    @PostMapping("/registration")
    public ResponseEntity<Users> registration(@RequestBody Users user) {
        try{
            return ResponseEntity.ok(userService.registration(user));
        } catch (SQLException | DataIntegrityViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
