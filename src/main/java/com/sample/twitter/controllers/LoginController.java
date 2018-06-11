package com.sample.twitter.controllers;

import com.sample.twitter.dao.UserDaoImpl;
import com.sample.twitter.model.UserBean;
import com.sample.twitter.model.UserDetails;
import com.sample.twitter.provider.GoogleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    GoogleProvider googleProvider;

    private UserDaoImpl userDao;


    @RequestMapping(value = "/google", method = RequestMethod.GET)
    public String loginToGoogle(Model model) {
        return googleProvider.getGoogleUserData(model, new UserBean());
    }
    @RequestMapping("/")
    public String index(){
        return  "home/index";

    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String loginSuccess( Model model){
        UserBean bean = new UserBean();
        googleProvider.getGoogleUserData(model, bean);
        UserDetails user =  new UserDetails();
        userDao.listUsers();
//     userService.addUser(user);

        return  "home/success";
    }




}
