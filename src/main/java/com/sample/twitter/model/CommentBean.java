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
    private User user;

    @Id
    @GeneratedValue
    @Column(name="comment_id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "likes")
    private int likes;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="parent_comment_id")
    private CommentBean parentComment;

    @OneToMany(mappedBy = "parentComment")
    private List<CommentBean> replies;

    public CommentBean() {
        this.user = null;
        this.comment = "Please Enter new Comments";
        this.likes = 0;
        this.parentComment =  null;
        this.replies = null;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public CommentBean getParentComment() {
        return parentComment;
    }

    public void setParentComment(CommentBean parentComment) {
        this.parentComment = parentComment;
    }

    public List<CommentBean> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentBean> replies) {
        this.replies = replies;
    }
}
