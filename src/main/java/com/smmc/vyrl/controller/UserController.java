package com.smmc.vyrl.controller;

import com.smmc.vyrl.domain.graph.User;
import com.smmc.vyrl.domain.relational.RUser;
import com.smmc.vyrl.service.graph.UserService;
import com.smmc.vyrl.service.relational.RUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RUserService rUserService;


    @PostMapping
    public User postUser() {
        return null;
    }


    @GetMapping("/find/{id}")
    public RUser findUser() {
        BigInteger id = new BigInteger("6");
        return rUserService.getUser(id);
    }

    @GetMapping("/follows/{nickname}")
    public User userFollow(final @PathVariable String nickname) {
        return userService.getUserFollow(nickname);
    }

    @GetMapping("/{nickname}")
    public User followForm(final @PathVariable String nickname) {
        return userService.getUser(nickname);
    }

    @GetMapping("/following/{me}/{target}")
    public void saveFollowUser(final @PathVariable long me, final @PathVariable long target) {
        userService.saveFollowUser(me, target);
    }

    @GetMapping("/articles/{userId}")
    public User getArticleList(final @PathVariable long userId) {
        return userService.getUser(userId);
    }
}
