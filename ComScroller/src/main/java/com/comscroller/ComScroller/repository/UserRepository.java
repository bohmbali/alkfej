package com.comscroller.ComScroller.repository;

import com.comscroller.ComScroller.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Bőhm Balázs
 */
@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

    Optional<Users> findByUsernameAndPassword(String username, String password);
    
    Optional<Users> findByUsernameAndBanned(String username, boolean ban);
    
    Optional<Users> findByUsernameAndRole(String username, Users.Role role);
}
