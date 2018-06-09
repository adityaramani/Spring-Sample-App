package com.sample.twitter.service;

import java.util.List;

import com.sample.twitter.model.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import  com.sample.twitter.dao.UserDao;


@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDAO(UserDao UserDAO) {
        this.userDao = UserDAO;
    }

    @Override
    @Transactional
    public void addUser(UserDetails p) {
        this.userDao.addUser(p);
    }

    @Override
    @Transactional
    public void updateUser(UserDetails p) {
        this.userDao.updateUser(p);
    }

    @Override
    @Transactional
    public List<UserDetails> listUsers() {
        return this.userDao.listUsers();
    }

    @Override
    @Transactional
    public UserDetails getUserByName(String name) {
        return this.userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public void removeUser(String name) {
         this.userDao.removeUser(name);
    }


}
