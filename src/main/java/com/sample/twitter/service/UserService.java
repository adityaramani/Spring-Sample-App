package com.sample.twitter.service;

import com.sample.twitter.model.User;
import com.sample.twitter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@Service
public class UserService {

    @Autowired
    UserRepository userDao;

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
    public User getUserById(Long id) {
        User userDetails = userDao.findOne(id);
        return userDetails;
    }

    public Long getUserIdByUsername(String username){
        //return userDao.findOne(username).getUserId();
        User user = userDao.findByUsername(username);

        if(user == null)
            return -1L;
        Long id = user.getUserId();

        return id;
    }

    public String getUsernameByUserId(Long id){
        return userDao.findById(id).getUsername();
    }


}