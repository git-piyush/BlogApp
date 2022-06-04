package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.DTO.PostRequestDto;
import com.blog.DTO.PostResponseDto;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
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

		PostRequestDto createdPost = convertEntityToPostRequestDto(newlyCreatedPost);
		return createdPost;
	}

	@Override
	public PostResponseDto getAllPost(int pageNo, int pageSize, String sortBy, String orderBy) {
		
		Sort sort = orderBy.equalsIgnoreCase(Sort.Direction.ASC.toString())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> allPostsInPageableForm = postRepository.findAll(pageable);
		/*
		 * Java 8 Stream's map method is intermediate operation and consumes single
		 * element from input Stream and produces single element to output Stream. It
		 * simply used to convert Stream of one type to another.
		 */
		List<Post> allPosts = allPostsInPageableForm.getContent();
		List<PostRequestDto> content =  allPosts.stream().map(post -> convertEntityToPostRequestDto(post)).collect(Collectors.toList());
		
		PostResponseDto postResponseDto = new PostResponseDto();
		
		postResponseDto.setPostList(content);
		postResponseDto.setPageNo(allPostsInPageableForm.getNumber());
		postResponseDto.setPageSize(allPostsInPageableForm.getSize());
		postResponseDto.setTotalPages(allPostsInPageableForm.getTotalPages());
		postResponseDto.setTotalElements(allPostsInPageableForm.getTotalElements());
		postResponseDto.setLast(allPostsInPageableForm.isLast());
		
		return postResponseDto;
	}

	private Post convertDtoToEntity(PostRequestDto postRequestDto) {
		Post newPost = new Post();
		newPost.setId(postRequestDto.getId());
		newPost.setTitle(postRequestDto.getTitle());
		newPost.setDescription(postRequestDto.getDescription());
		newPost.setContent(postRequestDto.getContent());
		return newPost;
	}

	private PostRequestDto convertEntityToPostRequestDto(Post newlyCreatedPost) {
		PostRequestDto post = new PostRequestDto();
		post.setId(newlyCreatedPost.getId());
		post.setTitle(newlyCreatedPost.getTitle());
		post.setDescription(newlyCreatedPost.getDescription());
		post.setContent(newlyCreatedPost.getContent());
		return post;
	}

	@Override
	public PostRequestDto getPostByPostId(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		return convertEntityToPostRequestDto(post);
	}

	@Override
	public PostRequestDto updatePost(PostRequestDto postRequestDto, Long postId) {

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		
		post.setTitle(postRequestDto.getTitle());
		post.setDescription(postRequestDto.getDescription());
		post.setContent(postRequestDto.getContent());
		
		Post updatedPost = postRepository.save(post);
		return convertEntityToPostRequestDto(updatedPost);
	}

	@Override
	public String deletePostByPostId(Long postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepository.deleteById(postId);
		return "Post has been deleted Successfully";
	}

}
