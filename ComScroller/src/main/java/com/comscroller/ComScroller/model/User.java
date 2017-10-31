package com.comscroller.ComScroller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Bőhm Balázs
 */
@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, unique = true)
    private String email;

   
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    
    @Column(nullable = false, unique = true)
    private String nickname;
    
    @Column()
    private String completed_games;
    
    @Column(nullable = false)
    private boolean banned;    
    
    public enum Role {
        GUEST, USER, ADMIN, MODERATOR
    }
}
