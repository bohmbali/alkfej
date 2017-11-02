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
import java.util.ArrayList;
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

    public Iterable<Games> list(Users user) {
        Users.Role role = user.getRole();
        if (role.equals(Users.Role.USER)) {
            return gameRepository.findAllByOwnerid(user.getId(), true);
        }
        return gameRepository.findAll();
    }

    public Games finish(Games game) {
        game.setFinished(!isFinished(game));
        gameRepository.save(game);
        return game;
    }

    public boolean isFinished(Games game) {
        return gameRepository.findByNameAndFinished(game.getName(), true).isPresent();
    }

    public void delete(Games game) {
        List<Scenes> scenes = sceneRepository.findAllByGameid(game.getId());
        for (Scenes scene : scenes) {
            Characters character = characterRepository.findById(scene.getCharacterid());
            characterRepository.delete(character);
            sceneRepository.delete(scene);
        }
        gameRepository.delete(game);
    }

    public void save(Games game, Iterable<Scenes> scenes, Iterable<Characters> characters) {
        gameRepository.save(game);
        for (Scenes scene : scenes) {
            sceneRepository.save(scene);
        }
        for (Characters character : characters) {
            characterRepository.save(character);
        }
    }

    public List<Object> getAll(int id) {
        List<Object> all = new ArrayList<>();
        List<Scenes> scenes = new ArrayList<>();
        List<Characters> characters = new ArrayList<>();        
        Games game = gameRepository.findOne(id);
        characters.add(characterRepository.findOne(game.getMaincharacter()));
        Integer parentId = game.getStartscene();
        childScenes(parentId, scenes,characters);
        for(Scenes scene : scenes){
            all.add(scene);
        }
        for(Characters character : characters){
            all.add(character);
        }
        return all;
    }

    public void childScenes(Integer parentId,List<Scenes> scenes,List<Characters> characters) {
        Scenes parent=sceneRepository.findOne(parentId);
        if(!scenes.contains(parent)){
        scenes.add(parent);
        }
        Characters character=characterRepository.findOne(parent.getCharacterid());
        if(!characters.contains(character)){
            characters.add(character);
        }        
        Integer childId;
        childId = parent.getAction1();
        if (childId != null) {
            childScenes(childId,scenes,characters);
            
        }
        childId = parent.getAction2();
        if (childId != null) {
            childScenes(childId,scenes,characters);
            
        }
        childId = parent.getAction3();
        if (childId != null) {
            childScenes(childId,scenes,characters);
            
        }
    }
}
