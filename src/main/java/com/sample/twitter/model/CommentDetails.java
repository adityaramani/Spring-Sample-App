package com.sample.twitter.model;

import java.util.ArrayList;
import java.util.List;

public class CommentDetails {

    CommentBean comment;
    private ArrayList<Long> repliesList;

    public CommentDetails(CommentBean comment) {
        this.comment = comment;
        this.repliesList = new ArrayList<Long>();
    }

    public CommentBean getCommentBean() {
        return comment;
    }

    public void setReplyIds(ArrayList<Long> repliesList) {
        this.repliesList= repliesList;
    }

    public void  addNewReply(long replyID){
        repliesList.add(replyID);
    }

}
