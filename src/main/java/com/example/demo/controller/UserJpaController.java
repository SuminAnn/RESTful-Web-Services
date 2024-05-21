package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    private UserRepository userRepository;

    public UserJpaController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //전체 사용자 조회 /jpa/users
    @GetMapping("/users")
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    //전체 사용자 조회 /jpa/user/{id}
    @GetMapping("/users/{id}")
    public ResponseEntity findUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException("id: " + id);
        }
        
        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder linTo = linkTo(methodOn(this.getClass()).findUsers());
        entityModel.add(linTo.withRel("all-users")); //all-users -> http://localhost:8088/jpa/users
        return ResponseEntity.ok(entityModel);
    }
}
