package com.emdemo.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class Article {
  @Id
  private String id;
  private String title;
  private String description;
  private String keywords;
  private String author;
  private boolean published;

  public Article() {

  }

  public Article(String title, String description, String keywords, String author, boolean published) {
    this.title = title;
    this.description = description;
    this.keywords = keywords;
    this.author = author;
    this.published = published;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  @Override
  public String toString() {
    return "Article [id=" + id + ", title=" + title + ", desc=" + description + ", keywords=" + keywords + ", author=" + author + ", published=" + published + "]";
  }
}
