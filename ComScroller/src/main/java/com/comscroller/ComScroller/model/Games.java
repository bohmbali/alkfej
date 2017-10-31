/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Catchy
 */
@Entity
@Table(name = "GAMES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Games {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(nullable =false)
    private Integer ownerid;

    @Column(nullable = false)
    private boolean ispublic;
    
    @Column(nullable = false)
    private boolean approved;
    
    @Column(nullable = false)
    private boolean finished;
    
    @Column(nullable = false)
    private Integer startscene;
    
    @Column()
    private Integer maincharacter;
    
    @Column(nullable = false)
    private Integer endscene1;
    
    @Column()
    private Integer endscene2;
    
    @Column()
    private Integer endscene3;
    
    @Column()
    private String description;
    
}
