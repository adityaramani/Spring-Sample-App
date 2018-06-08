package com.sample.twitter.model;


import java.io.Serializable;
import java.util.List;

public class CommentBean implements Serializable {

    private String userID;
    private String comment;
    private int likes;
    private List<CommentBean> replies;

    public void setUserID(String userID){
        this.userID = userID;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getComment(){
        return  this.comment;
    }
    public  String getUserID(){
        return this.userID;
    }
}
