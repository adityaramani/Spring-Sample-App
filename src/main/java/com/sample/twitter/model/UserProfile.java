/*package com.sample.twitter.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERPROFILE")
public class UserProfile {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="username")
    private UserDetails userDetails;

    @OneToMany(mappedBy = "id")
    private List<CommentBean> allComments;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<CommentBean> getAllComments() {
        return allComments;
    }

    public void setAllComments(List<CommentBean> allComments) {
        this.allComments = allComments;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userDetails=" + userDetails +
                '}';
    }
}
*/