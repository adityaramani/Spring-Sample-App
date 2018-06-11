package com.sample.twitter.model;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "COMMENTS")
public class CommentBean implements Serializable {



    @NotNull
    private String userID;

    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "likes")
    private int likes;


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
