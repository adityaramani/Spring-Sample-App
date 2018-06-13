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
    public void insertNormalCase() throws Exception{

        entityManager.persistAndFlush(new User("test"));
        User user = userRepository.findByUsername("test");

        assertNotNull(user);
        assertEquals(user.getUsername(), "test");
    }

    @Test
    public void insertNullCase() throws Exception{
        User user = userRepository.findByUsername("test");
        assertNull(user);
    }

    @Test
    public void findOneNormalCase() throws Exception{

        User user = new User("test");
        user.setUserId(1L);

        entityManager.merge(user);

        User test = userRepository.findOne(1L);

        assertNotNull(test);
        assertEquals(test.getUserId(), Long.valueOf(1L));
    }

    @Test
    public void findOneNullCase() {
        User test = userRepository.findOne(1L);

        assertNull(test);
    }

    @Test
    public void findOneWrongId(){
        User user = new User("test");
        user.setUserId(1L);

        entityManager.merge(user);

        User test = userRepository.findOne(2L);

        assertNull(test);

    }

    @Test
    public void findByUsernameNormalCase(){
        User user = new User("test");

        entityManager.merge(user);

        User test = userRepository.findByUsername("test");

        assertNotNull(test);
        assertEquals(test.getUsername(), "test");
    }
    


}
