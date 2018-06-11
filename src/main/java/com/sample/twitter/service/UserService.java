package com.sample.twitter.service;

import com.sample.twitter.model.User;
import com.sample.twitter.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(User p) {
       userDao.save(p);
    }

    public void updateUser(User p) {
        //TODO
    }

    public List<User> listUsers(){
        return userDao.findAll();
    }

    public void removeUser(String name) {
        //TODO

    }

    public User getUserByName(String name) {
        User userDetails = userDao.findOne(name);

        if (userDetails == null) {
            System.out.println("NULL");
            userDetails = new User();
        }
        return userDetails;
    }

}