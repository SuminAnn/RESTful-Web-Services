package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.bean.HelloWorldBean;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController //@Controller + @ResponseBody
public class HelloWorldController {
    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

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

    @GetMapping("/hello-world-internationalized")
    public String helloworldInternaionalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        
        return messageSource.getMessage("greeting.message", null, locale);
    }//객체타입
}
