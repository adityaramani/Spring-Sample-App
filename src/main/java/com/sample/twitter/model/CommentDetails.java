package com.sample.twitter.model;

import java.util.ArrayList;
import java.util.List;

public class CommentDetails {

    private Long id;
    private String userId;
    private String comment;
    private int likes;
    private ArrayList<Long> repliesList;

    public CommentDetails() {
        repliesList = new ArrayList<Long>();
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public int getLikes() {
        return likes;
    }

    public List<Long> getReplyIds() {
        return repliesList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setReplyIds(ArrayList<Long> repliesList) {
        this.repliesList= repliesList;
    }

    public void  addNewReply(long replyID){
        repliesList.add(replyID);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
