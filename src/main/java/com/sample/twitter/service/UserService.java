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
    UserRepository userRepository;

    @Transactional
    public void addUser(User p) {
       userRepository.save(p);
    }

    public void updateUser(User p) {
        //TODO
    }

    @Transactional
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public void removeUser(String name) {
        //TODO

    }

    @Transactional
    public User getUserById(Long id) {
        User userDetails = userRepository.findOne(id);

        return userDetails;
    }

    public Long getUserIdByUsername(String username){
        //return userRepository.findOne(username).getUserId();
        User user = userRepository.findByUsername(username);

        if(Objects.isNull(user))
            return -1L;
        return  user.getUserId();
    }

    public String getUsernameByUserId(Long id){
        return userRepository.findById(id).getUsername();
    }


}