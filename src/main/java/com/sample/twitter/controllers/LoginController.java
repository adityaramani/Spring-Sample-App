package com.sample.twitter.controllers;

import com.sample.twitter.identifier.SessionIdentifier;
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

        if(!model.containsAttribute("loggedInUser"))
            return "home/index";

        SessionIdentifier sessionIdentifier = new SessionIdentifier();

        String uuid = sessionIdentifier.getUserIdSource().getUserId();

        System.out.println(uuid);



        User user;
        long id = userService.getUserIdByUsername(bean.getEmail());
        System.out.println(id);
        if(id == -1)
            user = null;
        else
            user = userService.getUserById(id);


        if(user == null)
        {
            System.out.println("adding new user");

            user = new User();
            user.setUsername(bean.getEmail());
            userService.addUser(user);
        }

        List<CommentBean> allComments = commentService.getAllComments();



//        if(allComments.size() == 0){
//            System.out.println("Size is zero");
//            allComments.add(new CommentBean(user,"Please enter new comments"));
//        }

        model.addAttribute("allComments", allComments);
        return  "home/success";
    }


    @RequestMapping(value = "/create/comment", method = RequestMethod.POST)
    public String newComment(@ModelAttribute("commentForm")CommentBean commentBean, Model model) {
        UserBean bean = new UserBean();
        googleProvider.getGoogleUserData(model, bean);

        User userDetails = userService.getUserById(userService.getUserIdByUsername(bean.getEmail()));

        commentBean.setUser(userDetails);
        //commentBean.setParentComment(null);

        commentService.addComment(commentBean);

        return loginSuccess(model);
    }

    @RequestMapping(value = "/UserProfile/{userid}", method = RequestMethod.GET)
    public String getUserProfile(@PathVariable("userid") Long userId, Model model){

        System.out.println(userId);
        String username = userService.getUsernameByUserId(userId);
        model.addAttribute("username", username);
        User user = userService.getUserById(userId);
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
