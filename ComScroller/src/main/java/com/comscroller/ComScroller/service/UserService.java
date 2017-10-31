package com.comscroller.ComScroller.service;

import com.comscroller.ComScroller.model.Users;
import com.comscroller.ComScroller.repository.UserRepository;
import com.comscroller.ComScroller.service.exceptions.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.sql.SQLException;
import static com.comscroller.ComScroller.model.Users.Role.*;

/**
 * @author Bőhm Balázs
 */

@Service
@SessionScope
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private Users user;

    public Users login(Users user) throws UserNotValidException,UserIsBannedException {
        if (isValid(user)) {
            if(!isBanned(user)){
            return this.user = userRepository.findByUsername(user.getUsername()).get();
            }
            throw new UserIsBannedException();    
        }
            throw new UserNotValidException();
    }

    public Users registration(Users user) throws SQLException{
        user.setRole(USER);
        user.setBanned(false);        
        this.user = userRepository.save(user);        
        return user;
    }
    
    public Users changeRole(Users user, Users.Role role){
        user.setRole(role);
        userRepository.save(user);
        return user;
    }
    
    public Users ban(Users user){
        if(!isAdmin(user)){
            user.setBanned(!isBanned(user));
            userRepository.save(user);
        }
        return user;
    }
    
    
    public void delete(Users user){
        userRepository.delete(user);        
    }
    
    public Iterable<Users> users() {
        return userRepository.findAll();
    }
    
    public Users getUser(String username) {
        return userRepository.findByUsername(username).get();
        
    }
     
    public boolean isValid(Users user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }
    
    
    public boolean isBanned(Users user) {
        return userRepository.findByUsernameAndBanned(user.getUsername(), true).isPresent();
    }
    
    
     public boolean isModerator(Users user) {
        return userRepository.findByUsernameAndRole(user.getUsername(), MODERATOR).isPresent();
    }
     
     public boolean isAdmin(Users user) {
        return userRepository.findByUsernameAndRole(user.getUsername(), ADMIN).isPresent();
    }

    public boolean isLoggedIn() {
        return user != null;
    }
}
