package com.smmc.vyrl.service.graph;

import com.smmc.vyrl.domain.graph.Article;
import com.smmc.vyrl.domain.graph.User;
import com.smmc.vyrl.repo.graph.ArticleRepository;
import com.smmc.vyrl.repo.graph.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public void saveArticle(String nickname) {

        User user = userRepository.findByNickname(nickname);

        Article article = new Article();
        article.setTitle("title " + nickname);
        article.setContent(" content article " + nickname + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        article.setCreated_at(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        user.articleWrite(article);

        userRepository.save(user);
        articleRepository.save(article);

    }

}
