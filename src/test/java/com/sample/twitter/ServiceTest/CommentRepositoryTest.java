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

import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class CommentRepositoryTest {

	@Mock
	private CommentRepository mockCommentrepo;


	@InjectMocks
	private CommentService commentService;

	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);

	}



	@Test
	public void insertAndGetCommentTest(){

	}


	@Test
	public void getCommentByIdTest(){
		when(mockCommentrepo.findOne(anyLong())).thenReturn(new CommentBean());
		assertNotNull(commentService.getCommentById(1l));
	}

	@Test
	public void findByUserTest(){
		User user =  new User("TEST-USER");
		CommentBean commentBean = new CommentBean(user,"TEST-COMMENT");
		mockCommentrepo.save(commentBean);
		List<CommentBean>  comments= mockCommentrepo.findByUser(user);
		System.out.println(comments);
	}

}
