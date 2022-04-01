package com.emdemo.spring.data.news.repository;

import com.emdemo.spring.data.news.model.Article;
import com.emdemo.spring.data.news.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
