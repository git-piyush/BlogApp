package com.blog.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.DTO.PostRequestDto;
import com.blog.entity.Post;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;

/* In an application, the business logic resides within 
 * the service layer so we use the @Service Annotation to 
 * indicate that a class belongs to that layer. It is also 
 * a specialization of @Component Annotation like the 
 * @Repository Annotation.*/
@Service
public class PostServiceImpl implements PostService {

	@Autowired // @Autowired is one of the key annotation in annotation based Dependency
				// Injection.
	private PostRepository postRepository;

	/*
	 * @Autowired public void setPostRepository(PostRepository postRepository) {
	 * this.postRepository = postRepository; }
	 */
	/*
	 * @Autowired public PostServiceImpl(PostRepository postRepository) { super();
	 * this.postRepository = postRepository; }
	 */
	@Override
	public PostRequestDto createPost(PostRequestDto postRequestDto) {
		// convert dto to entity
		Post newPost = new Post();
		newPost.setId(postRequestDto.getId());
		newPost.setTitle(postRequestDto.getTitle());
		newPost.setDescription(postRequestDto.getDescription());
		newPost.setContent(postRequestDto.getContent());
		Post newlyCreatedPost = postRepository.save(newPost);
		PostRequestDto createdPost = new PostRequestDto();
		
		//convert entity to dto
		createdPost.setId(newlyCreatedPost.getId());
		createdPost.setTitle(newlyCreatedPost.getTitle());
		createdPost.setDescription(newlyCreatedPost.getDescription());
		createdPost.setContent(newlyCreatedPost.getContent());
		return createdPost;
	}

}
