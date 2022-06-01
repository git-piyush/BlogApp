package com.blog.service;

import org.springframework.stereotype.Service;

import com.blog.DTO.PostRequestDto;

/* In an application, the business logic resides within 
 * the service layer so we use the @Service Annotation to 
 * indicate that a class belongs to that layer. It is also 
 * a specialization of @Component Annotation like the 
 * @Repository Annotation.*/
@Service
public interface PostService {

	PostRequestDto createPost(PostRequestDto postRequestDto);
	
}
