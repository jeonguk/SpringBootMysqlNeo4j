package com.smmc.vyrl.repo.graph;

import com.smmc.vyrl.domain.graph.Article;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface ArticleRepository extends GraphRepository<Article> {
}

