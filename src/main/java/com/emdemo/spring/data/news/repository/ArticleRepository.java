package com.emdemo.spring.data.news.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emdemo.spring.data.news.model.Article;

public interface ArticleRepository extends MongoRepository<Article, String> {
  List<Article> findByPublished(boolean published);
  List<Article> findByTitleContaining(String title);
}
