package com.sample.twitter.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USER")
public class User implements Serializable {
    @Id
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(){

    }
    public User(String username) {
        this.username = username;
    }
}
