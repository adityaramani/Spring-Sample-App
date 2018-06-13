package com.sample.twitter.repositories;

import com.sample.twitter.model.CommentBean;
import com.sample.twitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentBean, Long>{


    /*
    public void addComment(CommentBean commentBean);

    public CommentBean getCommentById(long id);

    public ArrayList<CommentBean> getAllComments();
    */

    List<CommentBean> findByUser(User user);

}
