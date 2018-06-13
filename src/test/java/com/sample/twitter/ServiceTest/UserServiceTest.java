package com.sample.twitter.ServiceTest;

import com.sample.twitter.model.User;
import com.sample.twitter.repositories.UserRepository;
import com.sample.twitter.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Mock
    private UserRepository mockUserDao;


    @InjectMocks
    private UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getUserByIdNormalCase(){
        when(mockUserDao.findOne(anyLong())).thenReturn(new User());

        assertNotNull(userService.getUserById(1L));
    }
}
