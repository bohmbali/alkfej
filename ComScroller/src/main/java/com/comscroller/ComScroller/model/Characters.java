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
@Table(name = "CHARACTERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Characters {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
        
    @Column(nullable = false)
    private int gameid;
        
    @Column(nullable = false)
    private int type;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private int hp;
    
    @Column(nullable = false)
    private int ap;
    
    @Column(nullable = false)
    private int Str;
    
    @Column(nullable = false)
    private int Agi;
    
    @Column(nullable = false)
    private int Int;
    
    @Column(nullable = false)
    private int fate;
    
    @Column()
    private String items;
    
    @Column()
    private String picture;
    
    @Column(nullable = false)
    private int lastcheckpoint;
}
