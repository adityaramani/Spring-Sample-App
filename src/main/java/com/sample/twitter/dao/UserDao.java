package com.sample.twitter.dao;

import com.sample.twitter.model.UserDetails;

import java.util.List;


public interface UserDao {

    public void addUser(UserDetails p);
    public void updateUser(UserDetails p);
    public List<UserDetails> listUsers();
    public void removeUser(String name);
    public UserDetails getUserByName(String name);

}