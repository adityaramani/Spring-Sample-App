package com.sample.twitter.service;


import com.sample.twitter.model.UserDetails;

import java.util.List;


public interface UserService {

    public void addUser(UserDetails p);
    public void updateUser(UserDetails p);
    public List<UserDetails> listUsers();
    public UserDetails getUserByName(String name);
    public void removeUser(String name);

}

