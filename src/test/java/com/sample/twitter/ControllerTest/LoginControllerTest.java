package com.sample.twitter.ControllerTest;

import com.sample.twitter.controllers.LoginController;
import com.sample.twitter.model.CommentBean;
import com.sample.twitter.model.User;
import com.sample.twitter.model.UserBean;
import com.sample.twitter.provider.GoogleProvider;
import com.sample.twitter.repositories.UserRepository;
import com.sample.twitter.service.CommentService;
import com.sample.twitter.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.social.google.api.Google;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private MockMvc mvc;

    @Mock
    private UserService userService;

    @Mock
    private CommentService commentService;

    @Mock
    private GoogleProvider googleProvider;

    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders.standaloneSetup(loginController)
                .build();
    }

    @Test
    public void init() throws Exception {

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/index"))
                .andExpect(forwardedUrl("home/index"));
    }

    @Test
    public void relaodLoggedIn() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("_socialUserUUID", "TESTUSER");

        mvc.perform(get("/").session(mockHttpSession))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/success"));
    }

    @Test
    public void successNotLoggedInTest() throws Exception {

        mvc.perform(get("/success"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("home/index"));
    }

    @Test
    public void successLoggedInTest() throws Exception {
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("_socialUserUUID", "TESTUSER");
        when(userService.getUserById(anyLong())).thenReturn(new User());
        when(commentService.getAllComments()).thenReturn(new ArrayList<CommentBean>());


        mvc.perform(get("/success").session(mockHttpSession).flashAttr("loggedInUser", new UserBean()))
                .andExpect(status().isOk())
                .andExpect(view().name("home/success"))
                .andExpect(model().attributeExists("allComments"))
                .andExpect(forwardedUrl("home/success"));


    }



/*
    @Test
    public void loginTest( ) throws Exception{
        mvc.perform(post( "/connect/"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("loggedInUser"));
    }
    */
}