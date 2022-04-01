package com.emdemo.spring.data.news.controller;

import com.emdemo.spring.data.news.model.User;
import com.emdemo.spring.data.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api/v1/users/") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
    public ResponseEntity<User> addNewUser(@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            if (userRepository.getUsersByEmail(email) == null) {
                User n = new User();
                n.setName(name);
                n.setEmail(email);
                userRepository.save(n);
                return new ResponseEntity<>(n, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
