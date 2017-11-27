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
@Table(name = "SCENES")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Scenes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false)
    private Integer gameid;
        
    @Column(nullable = false)
    private Integer type;
    
    @Column()
    private String maintext;
    
    @Column()
    private String picture1;
    
    @Column()
    private String picture2;
    
    @Column()
    private Integer action1;
     
    @Column()
    private String actiontext1;
    
    @Column()
    private Integer action2;
     
    @Column()
    private String actiontext2;
    
    @Column()
    private Integer action3;
     
    @Column()
    private String actiontext3;
        
    @Column()
    private Integer characterid;
    
    @Column()
    private boolean ischeckpoint;
    
    @Column()
    private boolean isdeadpoint;
    
    @Column()
    private String items;
    
    @Column()
    private String requiredItems;
}
