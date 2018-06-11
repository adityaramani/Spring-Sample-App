package com.sample.twitter.dao;

import com.sample.twitter.model.CommentBean;
import com.sample.twitter.model.UserDetails;

import java.util.ArrayList;
import java.util.List;

public interface CommentDao {

    public void addComment(CommentBean commentBean);

    public CommentBean getCommentById(long id);

    public ArrayList<CommentBean> getAllComments();

}
