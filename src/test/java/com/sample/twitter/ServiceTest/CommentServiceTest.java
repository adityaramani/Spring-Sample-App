package com.sample.twitter.ServiceTest;

import com.sample.twitter.model.CommentBean;
import com.sample.twitter.repositories.CommentRepository;
import com.sample.twitter.service.CommentService;

import com.sample.twitter.model.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class CommentServiceTest {

	@Mock
	private CommentRepository mockCommentrepo;


	@InjectMocks
	private CommentService commentService;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void getCommentrByIdNormalCase(){
		when(mockCommentrepo.findOne(anyLong())).thenReturn(new CommentBean());

		assertNotNull(commentService.getCommentById(1L));
	}

	@Test
	public void getUserByIdWrongId(){
		when(mockCommentrepo.findOne(anyLong())).thenReturn(null);

		assertNull(commentService.getCommentById(1L));
	}

	@Test
	public  void addCommentTest(){
		when(mockCommentrepo.save(any(CommentBean.class))).thenReturn(null);
		assertNull(commentService.addComment(new CommentBean()));
	}

	@Test
	public  void getAllCommentsEmptyTest(){
		when(mockCommentrepo.findAll()).thenReturn( new ArrayList<CommentBean> ());
		assertEquals(0 , commentService.getAllComments().size());
	}

	@Test
	public  void getAllCommentsNormalTest(){
		when(mockCommentrepo.findAll()).thenReturn( new ArrayList<CommentBean> ());
		assertEquals(1, commentService.getAllComments().size()+1);
	}


}
