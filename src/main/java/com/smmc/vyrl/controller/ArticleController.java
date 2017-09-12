package com.smmc.vyrl.controller;

import com.smmc.vyrl.service.graph.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/save/{nickname}")
    public void followForm(final @PathVariable String nickname) {
        articleService.saveArticle(nickname);
    }

}
