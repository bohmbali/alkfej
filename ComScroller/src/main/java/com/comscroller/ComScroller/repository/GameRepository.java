/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.repository;

import com.comscroller.ComScroller.model.Games;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
/**
 *
 * @author Bohm_Balazs
 */
public interface GameRepository extends CrudRepository<Games, Integer> {
    
    List<Games> findAllByIspublicAndApproved(boolean isPublic, boolean approved);
    List<Games> findAllByApproved(boolean approved);
    List<Games> findAllByOwneridOrApproved(int id, boolean approved);
    
}
