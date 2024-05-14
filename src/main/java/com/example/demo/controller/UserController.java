package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDaoService;
import com.example.demo.exception.UserNotFoundException;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> findUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User findUser(@PathVariable int id) {
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID{%s} not found", id));
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
       User saveUser = service.save(user);

       URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/{id}")
            .buildAndExpand(saveUser.getId())
            .toUri();

        return ResponseEntity.created(location).build(); //HTTP Status Code 제어
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        User deleteUser = service.deleteById(id);

        if(deleteUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return ResponseEntity.noContent().build();
    }
}
