package com.emdemo.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emdemo.spring.data.mongodb.model.Article;
import com.emdemo.spring.data.mongodb.repository.ArticleRepository;

@RestController
@RequestMapping("/api/v1/")
public class ArticleController {

  @Autowired
  ArticleRepository ArticleRepository;

  @GetMapping("/articles")
  public ResponseEntity<List<Article>> getArticles(@RequestParam(required = false) String title) {
    try {
      List<Article> articles = new ArrayList<Article>();

      if (title == null)
        ArticleRepository.findAll().forEach(articles::add);
      else
        ArticleRepository.findByTitleContaining(title).forEach(articles::add);

      if (articles.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(articles, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/articles/{id}")
  public ResponseEntity<Article> getArticleById(@PathVariable("id") String id) {
    Optional<Article> ArticleData = ArticleRepository.findById(id);

    if (ArticleData.isPresent()) {
      return new ResponseEntity<>(ArticleData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/articles")
  public ResponseEntity<Article> createArticle(@RequestBody Article Article) {
    try {
      Article _Article = ArticleRepository.save(new Article(Article.getTitle(), Article.getDescription(), Article.getKeywords(), Article.getAuthor(), false));
      return new ResponseEntity<>(_Article, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/articles/{id}")
  public ResponseEntity<Article> updateArticle(@PathVariable("id") String id, @RequestBody Article Article) {
    Optional<Article> ArticleData = ArticleRepository.findById(id);
    if (ArticleData.isPresent()) {
      Article _Article = ArticleData.get();
      if(Article.getTitle() != null) _Article.setTitle(Article.getTitle());
      if(Article.getDescription()!= null) _Article.setDescription(Article.getDescription());
      if(Article.getKeywords()!= null) _Article.setKeywords(Article.getKeywords());
      if(Article.getAuthor()!= null) _Article.setAuthor(Article.getAuthor());
      if(!java.util.Objects.equals(Article.isPublished(),_Article.isPublished())) _Article.setPublished(Article.isPublished());
      return new ResponseEntity<>(ArticleRepository.save(_Article), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/articles/{id}")
  public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") String id) {
    try {
      ArticleRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/articles")
  public ResponseEntity<HttpStatus> deleteArticles() {
    try {
      ArticleRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/articles/published")
  public ResponseEntity<List<Article>> findByPublished() {
    try {
      List<Article> articles = ArticleRepository.findByPublished(true);

      if (articles.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(articles, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
