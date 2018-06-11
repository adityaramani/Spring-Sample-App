package com.sample.twitter.repositories;

import com.sample.twitter.model.CommentBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends JpaRepository<CommentBean, Long>{


    /*
    public void addComment(CommentBean commentBean);

    public CommentBean getCommentById(long id);

    public ArrayList<CommentBean> getAllComments();
    */

}
