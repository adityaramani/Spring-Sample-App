package com.sample.twitter.model;


import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "COMMENTS")
public class CommentBean implements Serializable {



    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private UserDetails user;

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "likes")
    private int likes;


    public void setComment(String comment){
        this.comment = comment;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public String getComment(){
        return  this.comment;
    }
}
