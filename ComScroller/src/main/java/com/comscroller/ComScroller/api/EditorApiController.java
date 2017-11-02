/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comscroller.ComScroller.api;

import com.comscroller.ComScroller.model.Characters;
import com.comscroller.ComScroller.model.Games;
import com.comscroller.ComScroller.model.Scenes;
import com.comscroller.ComScroller.model.Users;
import static com.comscroller.ComScroller.model.Users.Role.ADMIN;
import static com.comscroller.ComScroller.model.Users.Role.GUEST;
import static com.comscroller.ComScroller.model.Users.Role.MODERATOR;
import static com.comscroller.ComScroller.model.Users.Role.USER;
import com.comscroller.ComScroller.service.EditorService;
import com.comscroller.ComScroller.service.UserService;
import com.comscroller.ComScroller.service.annotations.Role;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Catchy
 */

@RestController
@RequestMapping("/api/editor")
public class EditorApiController {

    private final EditorService editorService;
    private final UserService userService;

    @Autowired
    public EditorApiController(EditorService editorService, UserService userService) {
        this.editorService = editorService;
        this.userService = userService;
    }

    @Role({USER, ADMIN, MODERATOR})
    @GetMapping
    public ResponseEntity<Iterable<Games>> Games() {
        Iterable<Games> games = editorService.list(userService.getUser());
        return ResponseEntity.ok(games);
    }
    
    @Role({USER,ADMIN,MODERATOR})
    @PutMapping("/finish")
    public ResponseEntity<Games> finish(@RequestBody Games game) {
        return ResponseEntity.ok(editorService.finish(game));  
    }
    
    @Role({USER,ADMIN,MODERATOR})
    @PutMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Games game, HttpServletResponse response, HttpServletRequest request) {
        editorService.delete(game);
        try {
            response.sendRedirect("/api/editor/");
        } catch (IOException e) {

            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(true);
    }
    
    @Role({USER,ADMIN,MODERATOR})
    @GetMapping("/create")
    public ResponseEntity<Games> create(HttpServletResponse response, HttpServletRequest request) {
        return ResponseEntity.ok(new Games());
    }
    
    @Role({USER,ADMIN,MODERATOR})
    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@RequestBody Games game, @RequestBody Iterable<Scenes> scenes,@RequestBody Iterable<Characters> characters, HttpServletResponse response, HttpServletRequest request) {
        editorService.save(game, scenes, characters);       
        return ResponseEntity.ok(true);
    }
    
    @Role({USER,ADMIN,MODERATOR})
    @GetMapping("/edit/{id}")
    public ResponseEntity<List<Object>> edit(@PathVariable int id,HttpServletResponse response, HttpServletRequest request) {
        return ResponseEntity.ok(editorService.getAll(id));
    }
    
    @Role({USER,ADMIN,MODERATOR})
    @PostMapping("/edit/{id}")
    public ResponseEntity<Boolean> edit(@RequestBody Games game, @RequestBody Iterable<Scenes> scenes,@RequestBody Iterable<Characters> characters, HttpServletResponse response, HttpServletRequest request) {
        editorService.save(game, scenes, characters);
       
        return ResponseEntity.ok(true);
    }
    
}