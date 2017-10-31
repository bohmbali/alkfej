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
@EqualsAndHashCode(callSuper = true)
public class Scenes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false)
    private int gameid;
        
    @Column(nullable = false)
    private int type;
    
    @Column()
    private String maintext;
    
    @Column()
    private String picture1;
    
    @Column()
    private String picture2;
    
    @Column()
    private int action1;
     
    @Column()
    private String actiontext1;
    
    @Column()
    private int action2;
     
    @Column()
    private String actiontext2;
    
    @Column()
    private int action3;
     
    @Column()
    private String actiontext3;
        
    @Column()
    private int characterid;
    
    @Column()
    private boolean ischeckpoint;
    
    @Column()
    private boolean isdeadpoint;
}
