package com.sample.twitter.dao;

import com.sample.twitter.model.CommentBean;
import com.sample.twitter.model.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommentDaoImpl implements CommentDao{

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public CommentDaoImpl(){
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("COMMENTS");
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
        }
    }

    @Override
    public void addComment(UserDetails userDetails, String comment){


        CommentBean commentBean = new CommentBean();

        commentBean.setUserID(userDetails.getUsername());
        commentBean.setComment(comment);

        entityTransaction.begin();

        entityManager.merge(commentBean);

        entityTransaction.commit();
    }

    @Override
    public CommentBean getCommentById(long id) {
        entityTransaction.begin();


        CommentBean commentBean = entityManager.find(CommentBean.class, id);

        entityTransaction.commit();

        return commentBean;
    }

    @Override
    public ArrayList<CommentBean> getAllComments(){
        entityTransaction.begin();

        Query query = entityManager.createQuery("select * from COMMENTS;");
        List allCommentsList = query.getResultList();

        ArrayList <CommentBean> commentBeanArrayList = new ArrayList<>(allCommentsList);

        entityTransaction.commit();

        return commentBeanArrayList;
    }
}
