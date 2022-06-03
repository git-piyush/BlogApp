package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.DTO.PostRequestDto;
import com.blog.DTO.PostResponseDto;
import com.blog.entity.Post;

/* In an application, the business logic resides within 
 * the service layer so we use the @Service Annotation to 
 * indicate that a class belongs to that layer. It is also 
 * a specialization of @Component Annotation like the 
 * @Repository Annotation.*/
@Service
public interface PostService {

	PostRequestDto createPost(PostRequestDto postRequestDto);
	
	List<PostRequestDto> getAllPost();
	
	PostRequestDto getPostByPostId(Long postId);
	
	PostResponseDto updatePost(PostRequestDto postRequestDto, Long postId);
	
}
