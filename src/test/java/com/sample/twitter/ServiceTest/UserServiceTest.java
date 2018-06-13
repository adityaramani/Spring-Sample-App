package com.sample.twitter.ServiceTest;

import com.sample.twitter.model.User;
import com.sample.twitter.repositories.UserRepository;
import com.sample.twitter.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private UserService userService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getUserByIdNormalCase(){
        when(userRepository.findOne(anyLong())).thenReturn(new User());

        assertNotNull(userService.getUserById(1L));
    }

    @Test
    public void getUserByIdWrongId(){
        when(userRepository.findOne(anyLong())).thenReturn(null);

        assertNull(userService.getUserById(1L));
    }

    @Test
    public void getUsernameByUserIdNormalCase(){
        when(userRepository.findById(1L)).thenReturn(new User("test"));

        assertEquals("test", userService.getUsernameByUserId(1L));

    }

    @Test
    public void getUserIDByUsernameNormalCase(){
        when(userRepository.findByUsername(anyString())).thenReturn(new User());

        assertEquals(userService.getUserIdByUsername("test"), null);
    }

    @Test
    public void getUserIDByUsernameNullCase(){
        when(userRepository.findByUsername(anyString())).thenReturn(null);

        assertEquals(userService.getUserIdByUsername("test"), Long.valueOf(-1L));
    }
}
