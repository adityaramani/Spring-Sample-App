package com.sample.twitter.controllers;

import com.sample.twitter.dao.CommentDaoImpl;
import com.sample.twitter.dao.UserDaoImpl;
import com.sample.twitter.model.CommentBean;
import com.sample.twitter.model.CommentDetails;
import com.sample.twitter.model.UserBean;
import com.sample.twitter.model.UserDetails;
import com.sample.twitter.provider.GoogleProvider;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    GoogleProvider googleProvider;

    @Autowired
    private HttpServletRequest request;
    private static final UserDaoImpl userDao =  new UserDaoImpl();
    private static final CommentDaoImpl commentDao =  new CommentDaoImpl();



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
        UserDetails user;
        user = userDao.getUserByName(bean.getEmail());
        if (user.getUsername() != bean.getEmail())
        {   user.setUsername(bean.getEmail()) ;
            userDao.addUser(user);
        }

        return  "home/success";
    }


    @RequestMapping(value = "/create/comment", method = RequestMethod.POST)
    public String newComment(@ModelAttribute("commentForm")CommentBean commentBean, Model model) {
        UserBean bean = new UserBean();
        googleProvider.getGoogleUserData(model, bean);

        UserDetails userDetails =  new UserDetails(bean.getEmail());

        CommentDetails commentDetails = new CommentDetails(commentBean);
        commentDetails.getCommentBean().setUser(userDetails);

        commentDao.addComment(commentDetails.getCommentBean());
        return  "home/success";
    }


    @RequestMapping(value = "/retieve/allComments", method = RequestMethod.GET)
    @ResponseBody
    public List<CommentDetails> getAllComments(){

       List<CommentBean> commentBeansList =  commentDao.getAllComments();
       List<CommentDetails> commentDetailsList = new ArrayList<CommentDetails>();

        for(CommentBean commentBean : commentBeansList){
                   CommentDetails details = new CommentDetails(commentBean );
                    commentDetailsList.add(details);
        }
        return commentDetailsList;
    }

}
