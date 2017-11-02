/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.service;

import com.comscroller.ComScroller.model.Characters;
import com.comscroller.ComScroller.model.Games;
import com.comscroller.ComScroller.model.Scenes;
import com.comscroller.ComScroller.model.Users;
import static com.comscroller.ComScroller.model.Users.Role.ADMIN;
import static com.comscroller.ComScroller.model.Users.Role.GUEST;
import static com.comscroller.ComScroller.model.Users.Role.MODERATOR;
import static com.comscroller.ComScroller.model.Users.Role.USER;
import com.comscroller.ComScroller.repository.CharacterRepository;
import com.comscroller.ComScroller.repository.GameRepository;
import com.comscroller.ComScroller.repository.SceneRepository;
import com.comscroller.ComScroller.service.annotations.Role;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author Catchy
 */
@Service
@SessionScope
@Data
public class EditorService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private SceneRepository sceneRepository;
    @Autowired
    private CharacterRepository characterRepository;

    public Iterable<Games> list(Users user){
        Users.Role role = user.getRole();
        if (role.equals(Users.Role.USER)) {
            return gameRepository.findAllByOwnerid(user.getId(),true);
        }
            return gameRepository.findAll();
    }
    public Games finish(Games game){
        game.setFinished(!isFinished(game));
        gameRepository.save(game);
        return game;
    }
    public boolean isFinished(Games game) {
        return gameRepository.findByNameAndFinished(game.getName(), true).isPresent();
    }
    public void delete(Games game){
        List<Scenes> scenes = sceneRepository.findAllByGameid(game.getId());
        for(Scenes scene : scenes){
            Characters character = characterRepository.findById(scene.getCharacterid()); 
            characterRepository.delete(character);
            sceneRepository.delete(scene); 
        } 
        gameRepository.delete(game); 
    }
    public void create(Games game, Iterable<Scenes> scenes, Iterable<Characters> characters){
        gameRepository.save(game);
        for(Scenes scene : scenes){
            sceneRepository.save(scene); 
        }
        for(Characters character : characters){
            characterRepository.save(character); 
        }
    }
}
