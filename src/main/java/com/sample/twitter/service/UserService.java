package com.sample.twitter.service;

import com.sample.twitter.model.User;
import com.sample.twitter.repositories.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public void addUser(User p) {
       userDao.save(p);
    }

    public void updateUser(User p) {
        //TODO
    }

    @Transactional
    public List<User> listUsers(){
        return userDao.findAll();
    }

    public void removeUser(String name) {
        //TODO

    }

    @Transactional
    public User getUserByName(String name) {
        User userDetails = userDao.findOne(name);

        if (userDetails == null) {
            System.out.println("NULL");
            userDetails = new User();
        }
        return userDetails;
    }

}