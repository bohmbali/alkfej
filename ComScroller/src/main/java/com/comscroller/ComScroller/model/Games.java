/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author Catchy
 */
@Entity
@Table(name = "GAMES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Games {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column()
    private int ownerid;

    @Column(nullable = false)
    private boolean ispublic;
    
    @Column(nullable = false)
    private boolean approved;
    
    @Column(nullable = false)
    private boolean finished;
    
    @Column(nullable = false)
    private int startscene;
    
    @Column()
    private int maincharacter;
    
    @Column(nullable = false)
    private int endscene1;
    
    @Column()
    private int endscene2;
    
    @Column()
    private int endscene3;
    
}
