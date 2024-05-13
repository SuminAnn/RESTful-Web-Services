package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloWorldController {
    // GET
    // URI : /hello-world
    // @RequestMapping(method=Request.GET, path="/hello-world")
    @GetMapping("/hello-world")
    public String helloworld() {
        return "Hello World";
    }
    
}
