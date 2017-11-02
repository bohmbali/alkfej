/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.api;

import com.comscroller.ComScroller.model.*;
import com.comscroller.ComScroller.service.*;
import com.comscroller.ComScroller.service.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.comscroller.ComScroller.service.annotations.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import org.springframework.dao.DataIntegrityViolationException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.comscroller.ComScroller.model.Users.Role.*;

/**
 *
 * @author Bohm_Balazs
 */
@RestController
@RequestMapping("/api/game")
public class GameApiController {

    private final GameService gameService;

    private final UserService userService;

    @Autowired
    public GameApiController(GameService gameService, UserService userService) {
        this.gameService = gameService;
        this.userService = userService;
    }

    @Role({GUEST, USER, ADMIN, MODERATOR})
    @GetMapping
    public ResponseEntity<Iterable<Games>> listGames() {
        Iterable<Games> games = gameService.list(userService.getUser());
        return ResponseEntity.ok(games);
    }

    @Role({GUEST, USER, ADMIN, MODERATOR})
    @GetMapping("/view/{id}")
    public ResponseEntity<Games> view(@PathVariable int id) {
        return ResponseEntity.ok(gameService.view(id));
    }

    @Role({GUEST, USER, ADMIN, MODERATOR})
    @GetMapping("/play/{id}")
    public ResponseEntity<Scenes> play(@PathVariable int id, HttpServletResponse response, HttpServletRequest request) {
        Games game = gameService.view(id);
        if (gameService.getMainCharacter() == null) {
            if (game.getMaincharacter() == null) {
                try {
                    response.sendRedirect(request.getRequestURL() + "/" + userService.getUser().getUsername() + "/character");
                } catch (IOException e) {
                    return ResponseEntity.badRequest().build();
                }
            } else {
                gameService.mainCharacter(game.getMaincharacter());
            }
        } else {
            Scenes scene = gameService.start(game.getStartscene(), game);
            if (scene.getCharacterid() != null) {
                gameService.setCharacter(scene.getCharacterid());
            }
            return ResponseEntity.ok(scene);
        }
        return ResponseEntity.badRequest().build();
    }

    
    @Role({GUEST, USER, ADMIN, MODERATOR})
    @GetMapping("/play/{id}/{username}/character")
    public ResponseEntity<Characters> createMainCharacter(@PathVariable int id, @PathVariable String username) {
        return ResponseEntity.ok(new Characters());
    }
    
    
    @Role({GUEST, USER, ADMIN, MODERATOR})
    @PostMapping("/play/{id}/{username}/character")
    public ResponseEntity<Characters> createMainCharacter(@PathVariable int id, @PathVariable String username, HttpServletResponse response, HttpServletRequest request, @RequestBody Characters character) {
        
        if (character != null) {
            gameService.setMainCharacter(character);
            try {
                response.sendRedirect("/api/game/play/" + id);
            } catch (IOException e) {
                
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(character);
        }
        return ResponseEntity.badRequest().build();
    }

}