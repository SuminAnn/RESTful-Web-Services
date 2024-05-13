package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController //@Controller + @ResponseBody
public class HelloWorldController {
    // GET
    // URI : /hello-world
    // @RequestMapping(method=Request.GET, path="/hello-world")
    @GetMapping("/hello-world")
    public String helloworld() {
        return "Hello World";
    }//문자열

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloworldBean() {
        return new HelloWorldBean("Hello World!");
    }//객체타입

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloworldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
