package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

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
		Post newlyCreatedPost = postRepository.save(convertDtoToEntity(postRequestDto));
		
		PostRequestDto createdPost = convertEntityToDto(newlyCreatedPost);
		return createdPost;
	}

	

	@Override
	public List<PostRequestDto> getAllPost() {

		List<Post> allPosts = postRepository.findAll();
		/*Java 8 Stream's map method is intermediate operation and consumes 
		 * single element from input Stream and produces single element to output Stream.
		 * It simply used to convert Stream of one type to another.
		 */
		return allPosts.stream().map(post -> convertEntityToDto(post)).collect(Collectors.toList());

	}

	private Post convertDtoToEntity(PostRequestDto postRequestDto) {
		Post newPost = new Post();
		newPost.setId(postRequestDto.getId());
		newPost.setTitle(postRequestDto.getTitle());
		newPost.setDescription(postRequestDto.getDescription());
		newPost.setContent(postRequestDto.getContent());
		return newPost;
	}
	
	private PostRequestDto convertEntityToDto(Post newlyCreatedPost) {
		PostRequestDto createdPost = new PostRequestDto();
		createdPost.setId(newlyCreatedPost.getId());
		createdPost.setTitle(newlyCreatedPost.getTitle());
		createdPost.setDescription(newlyCreatedPost.getDescription());
		createdPost.setContent(newlyCreatedPost.getContent());
		return createdPost;
	}

}
