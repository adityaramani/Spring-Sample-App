package com.sample.twitter.repositories;


import com.sample.twitter.model.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepo extends JpaRepository<UserBean, Integer> {

    // custom query to search to blog post by title or content

}
