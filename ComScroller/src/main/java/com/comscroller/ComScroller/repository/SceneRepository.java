/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.repository;

import com.comscroller.ComScroller.model.Scenes;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Bohm_Balazs
 */
public interface SceneRepository extends CrudRepository<Scenes, Integer> {
    List<Scenes> findAllByGameid(Integer id);
}
