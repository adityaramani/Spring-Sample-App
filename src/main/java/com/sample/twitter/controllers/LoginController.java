package com.sample.twitter.controllers;

import com.sample.twitter.service.CommentService;
import com.sample.twitter.service.UserService;
import com.sample.twitter.model.CommentBean;
import com.sample.twitter.model.UserBean;
import com.sample.twitter.model.User;
import com.sample.twitter.provider.GoogleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    GoogleProvider googleProvider;

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;



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
        User user;
        user  = userService.getUserByName(bean.getEmail().substring(0, bean.getEmail().indexOf('@')));
        try {
            System.out.println(user.getUsername());
        }
        catch (NullPointerException e){
            System.out.println("Null");
        }
        if (user == null)
        {   System.out.println("adding new user");

            user =  new User(bean.getEmail().substring(0,bean.getEmail().indexOf('@')));
            userService.addUser(user);
        }

        List<CommentBean> allComments = commentService.getAllComments();



        if(allComments.size() == 0){
            System.out.println("Size is zero");
            allComments.add(new CommentBean(user,"Please enter new comments"));
        }

        model.addAttribute("allComments", allComments);
        return  "home/success";
    }


    @RequestMapping(value = "/create/comment", method = RequestMethod.POST)
    public String newComment(@ModelAttribute("commentForm")CommentBean commentBean, Model model) {
        UserBean bean = new UserBean();
        googleProvider.getGoogleUserData(model, bean);

        User userDetails =  new User(bean.getEmail().substring(0, bean.getEmail().indexOf('@')));

        commentBean.setUser(userDetails);
        commentBean.setParentComment(null);

        commentService.addComment(commentBean);

        return loginSuccess(model);
    }

    @RequestMapping(value = "/UserProfile/{username}", method = RequestMethod.GET)
    public String getUserProfile(@PathVariable("username") String username, Model model){

        System.out.println(username);
        model.addAttribute("username", username);
        User user = userService.getUserByName(username);
        List<CommentBean> allCommentsByUser;
        if(user == null){
            allCommentsByUser = new ArrayList<>();
            allCommentsByUser.add(new CommentBean(user,"This user does not exist"));
        }

        else{

            allCommentsByUser = commentService.getAllCommentsByUser(user);

            if(allCommentsByUser == null){
                allCommentsByUser.add(new CommentBean(user,"This user has no comments"));
            }
            else{

                model.addAttribute("allCommentsByUser", allCommentsByUser);
            }
        }

        return "home/UserProfile";

    }


}
