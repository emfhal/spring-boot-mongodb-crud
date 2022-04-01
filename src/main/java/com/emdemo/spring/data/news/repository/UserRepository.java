package com.emdemo.spring.data.news.repository;

import com.emdemo.spring.data.news.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, CrudRepository<User, Integer> {
    @Query(value = "select * from user u where u.email= :email", nativeQuery = true)
    User getUsersByEmail(String email);

    @Query(value = "select * from user u where u.name= :name", nativeQuery = true)
    List<User> getUsersByName(String name);


    @Modifying
    @Query(value = "delete from author a where a.email= :email", nativeQuery = true)
    void deleteUserByEmail(@Param("email") String email);

    @Modifying
    @Query(value = "update user set name= :name where email = :email", nativeQuery = true)
    void updateUserNameByEmail(String name, String email);

}
