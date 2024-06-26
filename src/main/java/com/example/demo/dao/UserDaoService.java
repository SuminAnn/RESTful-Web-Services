package com.example.demo.dao;

import org.springframework.stereotype.Component;

import com.example.demo.bean.User;

import java.util.*;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static{
        users.add(new User(1, "Kim", new Date(), "test1", "111111-1111111"));
        users.add(new User(2, "Lee", new Date(), "test2", "222222-2222222"));
        users.add(new User(3, "An", new Date(), "test3", "333333-3333333"));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }

        users.add(user);

        return user;
    }


    public User findOne(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }

        return null;
    }
}
