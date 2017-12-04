/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.api;

import com.comscroller.ComScroller.model.*;
import com.comscroller.ComScroller.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.comscroller.ComScroller.service.annotations.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.comscroller.ComScroller.model.Users.Role.*;
import java.math.BigInteger;

/**
 *
 * @author Bohm_Balazs
 */
@RestController
@RequestMapping("/api/game")
public class GameApiController {

    private final GameService gameService;

    private final UserService userService;

    private BigInteger number = BigInteger.ZERO;

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
        Users staff = userService.getUser();
        if (((userService.isLoggedIn() || game.isIspublic()) && game.isApproved() && game.isFinished()) || userService.isAdmin(staff) || userService.isModerator(staff)) {
            if (gameService.getMainCharacter() == null) {
                if (game.getMaincharacter() == null) {
                    try {
                        System.out.println("try redirect");
                        String username = userService.getUser().getUsername();
                        if (username == null) {
                            username = "GUEST" + number.toString();
                            number.add(BigInteger.ONE);
                        }
                        response.sendRedirect(request.getRequestURL() + "/" + username + "/character");
                    } catch (IOException e) {
                        System.out.println("cannot redirect");
                        return ResponseEntity.badRequest().build();
                    }
                } else {
                    gameService.mainCharacter(game.getMaincharacter());

                }
            }
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

    @Role({GUEST, USER, ADMIN, MODERATOR})
    @GetMapping("/play/{id}/{scene}")
    public ResponseEntity<Scenes> next(@PathVariable int id, @PathVariable int nextScene, HttpServletResponse response, HttpServletRequest request) {
        Users staff = userService.getUser();
        Games game = gameService.getGame();
        if (((userService.isLoggedIn() || game.isIspublic()) && game.isApproved() && game.isFinished()) || userService.isAdmin(staff) || userService.isModerator(staff)) {

            Scenes scene = gameService.next(nextScene);
            if (scene.getCharacterid() != null) {
                gameService.setCharacter(scene.getCharacterid());
                Integer charType = gameService.getCharacter().getType();
                if (charType == 3) {
                    //TODO figth
                }
            }

            if (nextScene == gameService.getGame().getEndscene1() || nextScene == gameService.getGame().getEndscene2() || nextScene == gameService.getGame().getEndscene3()) {
                Users user = userService.getUser();
                user.setCompleted_games(user.getCompleted_games() + ";" + gameService.getGame().getName());
                userService.getUserRepository().save(user);
                
            }
            if (scene.isIsdeadpoint()) {
                
            }

            String items = scene.getItems();
            if (items != null) {
                gameService.getMainCharacter().setItems(items);
            }
            String items1 = gameService.next(scene.getAction1()).getRequiredItems();
            String items2 = gameService.next(scene.getAction2()).getRequiredItems();
            String items3 = gameService.next(scene.getAction3()).getRequiredItems();
            if (items1 != null) {
                if (!gameService.haveRequiredItems(items1)) {
                    
                }
            }
            if (items2 != null) {
                if (!gameService.haveRequiredItems(items2)) {
                    
                }
            }
            if (items3 != null) {
                if (!gameService.haveRequiredItems(items3)) {
                    
                }
            }

            return ResponseEntity.ok(scene);
        }
        return ResponseEntity.badRequest().build();
    }

}
