/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.repository;

import com.comscroller.ComScroller.model.Characters;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Bohm_Balazs
 */
public interface CharacterRepository extends CrudRepository<Characters, Integer> {
    Characters findById(Integer id);
}
