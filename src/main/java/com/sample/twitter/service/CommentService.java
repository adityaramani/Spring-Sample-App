package com.sample.twitter.service;

import com.sample.twitter.model.CommentBean;
import com.sample.twitter.model.User;
import com.sample.twitter.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService{

    @Autowired
    CommentRepository commentRepository;


    public void addComment(CommentBean commentBean){

        this.commentRepository.save(commentBean);

    }


    public CommentBean getCommentById(long id) {

        return this.commentRepository.findOne(id);
    }

    public List<CommentBean> getAllComments(){
        return this.commentRepository.findAll();
    }

    public List<CommentBean> getAllCommentsByUser(User user){
        return commentRepository.findByUser(user);
    }
}
