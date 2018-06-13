package com.sample.twitter.RepositoryTest;


import com.sample.twitter.TwitterApplication;
import com.sample.twitter.TwitterApplicationTests;
import com.sample.twitter.model.User;
import com.sample.twitter.repositories.UserRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TwitterApplication.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void insertNormalcase() throws Exception{

        entityManager.persistAndFlush(new User("test"));
        User user = userRepository.findByUsername("test");

        assertNotNull(user);
        assertEquals(user.getUsername(), "test");
    }
}
