package com.comscroller.ComScroller.service;

import com.comscroller.ComScroller.model.User;
import com.comscroller.ComScroller.repository.UserRepository;
import com.comscroller.ComScroller.service.exceptions.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import static com.comscroller.ComScroller.model.User.Role.*;

/**
 * @author Bőhm Balázs
 */

@Service
@SessionScope
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User user;

    public User login(User user) throws UserNotValidException,UserIsBannedException {
        if (isValid(user)) {
            if(!isBanned(user)){
            return this.user = userRepository.findByUsername(user.getUsername()).get();
            }
            throw new UserIsBannedException();    
        }
            throw new UserNotValidException();
    }

    public User registration(User user) {
        user.setRole(USER);
        user.setBanned(false);
        System.out.print("kész");
        this.user = userRepository.save(user);
        return user;
    }
    
    public User changeRole(User user, User.Role role){
        user.setRole(role);
        userRepository.save(user);
        return user;
    }
    
    public User ban(User user){
        if(!isAdmin(user)){
            user.setBanned(!isBanned(user));
            userRepository.save(user);
        }
        return user;
    }
    
    
    public void delete(User user){
        userRepository.delete(user);        
    }
    
    public Iterable<User> users() {
        return userRepository.findAll();
    }
     
    public boolean isValid(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }
    
    
    public boolean isBanned(User user) {
        return userRepository.findByUsernameAndBanned(user.getUsername(), true).isPresent();
    }
    
    
     public boolean isModerator(User user) {
        return userRepository.findByUsernameAndRole(user.getUsername(), MODERATOR).isPresent();
    }
     
     public boolean isAdmin(User user) {
        return userRepository.findByUsernameAndRole(user.getUsername(), ADMIN).isPresent();
    }

    public boolean isLoggedIn() {
        return user != null;
    }
}
