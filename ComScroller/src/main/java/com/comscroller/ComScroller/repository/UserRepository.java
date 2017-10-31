package com.comscroller.ComScroller.repository;

import com.comscroller.ComScroller.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bőhm Balázs
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
    
    Optional<User> findByUsernameAndBanned(String username, boolean ban);
    
    Optional<User> findByUsernameAndRole(String username, User.Role role);
}
