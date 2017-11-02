/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.service;

import org.springframework.stereotype.Service;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.annotation.SessionScope;
import com.comscroller.ComScroller.repository.*;
import com.comscroller.ComScroller.model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Bohm_Balazs
 */
@Service
@SessionScope
@Data
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private SceneRepository sceneRepository;
    @Autowired
    private CharacterRepository characterRepository;
    private Games game;
    private Scenes checkpoint;
    private Characters mainCharacter;
    private Characters character;

    public Iterable<Games> list(Users user) {
        Users.Role role = user.getRole();
        if (role.equals(Users.Role.GUEST)) {
            return gameRepository.findAllByIspublicAndApproved(true, true);
        } else if (role.equals(Users.Role.USER)) {
            return gameRepository.findAllByOwneridOrApproved(user.getId(), true);
        }
        return gameRepository.findAll();
    }

    public Games view(int id) {
        return gameRepository.findOne(id);

    }

    public Scenes start(int id, Games game) {
        this.game = game;
        return sceneRepository.findOne(id);
    }

    public void mainCharacter(int id) {
        mainCharacter = characterRepository.findOne(id);
    }

    public void setCharacter(int id) {
        character = characterRepository.findOne(id);
    }

    public Scenes next(int id) {
        Scenes scene = sceneRepository.findOne(id);
        if (scene.isCheckpoint()) {
            this.checkpoint = scene;
        }
        return scene;
    }

    public boolean haveRequiredItems(String items) {

        String[] splitRequired = items.split(";");
        List<String> requiredItems = Arrays.asList(splitRequired);
        Collections.sort(requiredItems);
        String[] split = character.getItems().split(";");
        List<String> ownedItems = Arrays.asList(split);
        Collections.sort(ownedItems);
        int i = 0;
        boolean haveIt = true;
        for (String item : requiredItems) {
            if (haveIt) {
                if (i + 1 > ownedItems.size()) {
                    haveIt = false;
                } else {
                    haveIt = item.equals(ownedItems.get(i));
                }
            }
            i++;
        }
        return haveIt;
    }
}
