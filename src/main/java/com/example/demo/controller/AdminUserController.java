package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.AdminUser;
import com.example.demo.bean.AdminUserV2;
import com.example.demo.bean.User;
import com.example.demo.dao.UserDaoService;
import com.example.demo.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue findUsersforAdmin() {
        List<User> users = service.findAll();
        
        List<AdminUser> adminUsers = new ArrayList<>();
        AdminUser adminUser = null;

        for (User user : users) {
            adminUser = new AdminUser();
            BeanUtils.copyProperties(user, adminUser);
            adminUsers.add(adminUser);
        }

        
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUsers);
        mapping.setFilters(filters);
        
        
        return mapping;
    }

    //@GetMapping("/v1/users/{id}") URI를 이용한 버전 관리
    //@GetMapping(value = "/users/{id}", params = "version=1") //파라미터를 이용한 버전 관리
    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1") //헤더를 이용한 버전 관리
    public MappingJacksonValue findUserforAdmin(@PathVariable int id) {
        User user = service.findOne(id);

        AdminUser adminUser = new AdminUser();

        if(user == null){
            throw new UserNotFoundException(String.format("ID{%s} not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    //@GetMapping("/v2/users/{id}") URI를 이용한 버전 관리
    //@GetMapping(value = "/v2/users/{id}", params = "version=2") //파라미터를 이용한 버전 관리
    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2") //헤더를 이용한 버전 관리
    public MappingJacksonValue findUserforAdminV2(@PathVariable int id) {
        User user = service.findOne(id);

        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null){
            throw new UserNotFoundException(String.format("ID{%s} not found", id));
        }else{
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP"); //grade 새로운 필드 추가
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

   
}
