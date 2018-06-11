package com.sample.twitter.service;

import com.sample.twitter.model.CommentBean;
import com.sample.twitter.repositories.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService{

    @Autowired
    CommentDao commentDao;


    public void addComment(CommentBean commentBean){

        this.commentDao.save(commentBean);

    }


    public CommentBean getCommentById(long id) {

        return this.commentDao.findOne(id);
    }

    public List<CommentBean> getAllComments(){
        return this.commentDao.findAll();
    }
}
