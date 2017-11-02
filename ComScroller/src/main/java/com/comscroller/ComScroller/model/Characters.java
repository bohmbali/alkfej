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
@EqualsAndHashCode()
public class Characters {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
        
    @Column(nullable = false)
    private Integer gameid;    
    
    @Column(nullable = false)
    private Integer userid;
        
    @Column(nullable = false)
    private Integer type;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Integer hp;
    
    @Column(nullable = false)
    private Integer ap;
    
    @Column(nullable = false)
    private Integer Str;
    
    @Column(nullable = false)
    private Integer Agi;
    
    @Column(nullable = false)
    private Integer Int;
    
    @Column(nullable = false)
    private Integer fate;
    
    @Column()
    private String items;
    
    @Column()
    private String picture;
    
    @Column(nullable = false)
    private Integer lastcheckpoint;
    
    public void addItems(String item){
        this.items=this.items + ";" + item;
    }
}
