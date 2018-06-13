package com.sample.twitter.service;

import com.sample.twitter.model.CommentBean;
import com.sample.twitter.model.User;
import com.sample.twitter.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService{

    @Autowired
    CommentRepository commentRepository;


    public CommentBean addComment(CommentBean commentBean){

        this.commentRepository.save(commentBean);
        return  commentBean;
    }


    public CommentBean getCommentById(long id) {

        return this.commentRepository.findOne(id);
    }

    public List<CommentBean> getAllComments(){
        List<CommentBean> allComments =  this.commentRepository.findAll();

        return allComments;
    }

    public List<CommentBean> getAllCommentsByUser(User user){
        return commentRepository.findByUser(user);
    }
}
