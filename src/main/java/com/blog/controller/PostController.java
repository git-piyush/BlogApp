package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.DTO.PostRequestDto;
import com.blog.DTO.PostResponseDto;
import com.blog.service.PostService;
import com.blog.utils.AppConstants;

/*@RestController is a convenience annotation for creating Restful controllers.
 * This mark controller classes as a request handler to allow Spring to recognize
 * it as a RESTful service during runtime.
 * 
 * @Controller + @ResponseBody
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	/*
	 * @PostMapping annotation maps HTTP POST requests onto specific handler
	 * methods. It is a composed annotation that acts as a shortcut for
	 * 
	 * @RequestMapping(method = RequestMethod.POST).
	 */
	@PostMapping
	public ResponseEntity<PostRequestDto> createPost(@RequestBody PostRequestDto postRequestDto) {
		/*
		 * By using @RequestBody annotation you will get your values mapped with the
		 * model you created in your system for handling any specific call. While by
		 * using @ResponseBody you can send anything back to the place from where the
		 * request was generated. Both things will be mapped easily without writing any
		 * custom parser etc. // http message converter
		 * 
		 * @RequestBody annotation maps the HttpRequest body to a transfer or domain
		 * object, enabling automatic deserialization of the inbound HttpRequest body
		 * onto a Java object.
		 */

		/*
		 * ResponseEntity represents the whole HTTP response: status code, headers, and
		 * body. As a result, we can use it to fully configure the HTTP response.
		 */
		return new ResponseEntity<PostRequestDto>(postService.createPost(postRequestDto), HttpStatus.CREATED);

	}

	@GetMapping
	public ResponseEntity<PostResponseDto> getAllPost(
			@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(name="sortBy", defaultValue = AppConstants.DEFAULT_SORTBY, required = false) String sortBy,
			@RequestParam(name="orderBy", defaultValue = AppConstants.DEFAULT_ORDERBY, required = false) String orderBy){
		/*
		 * The @PathVariable annotation is used for data passed in the URI (e.g. RESTful
		 * web services) while @RequestParam is used to extract the data found in query
		 * parameters.
		 */
		// return postService.getAllPost();
		return new ResponseEntity<PostResponseDto>(postService.getAllPost(pageNo, pageSize, sortBy, orderBy), HttpStatus.OK);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostRequestDto> getPostByPostId(@PathVariable(name = "postId") Long postId) {
		/*
		 * The @PathVariable annotation is used for data passed in the URI (e.g. RESTful
		 * web services) while @RequestParam is used to extract the data found in query
		 * parameters.
		 */
		return new ResponseEntity<PostRequestDto>(postService.getPostByPostId(postId), HttpStatus.OK);

	}

	@PutMapping("/{postId}")
	public ResponseEntity<PostRequestDto> updatePost(@RequestBody PostRequestDto postRequestDto,
			@PathVariable(name = "postId") Long postId) {
		/*
		 * @RequestBody annotation maps the HttpRequest body to a transfer or domain
		 * object, enabling automatic deserialization of the inbound HttpRequest body
		 * onto a Java object.
		 */
		PostRequestDto postResponseDto = postService.updatePost(postRequestDto, postId);

		return new ResponseEntity<PostRequestDto>(postResponseDto, HttpStatus.OK);

	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<String> deletePostByPostId(@PathVariable(name = "postId") Long postId) {

		return new ResponseEntity<String>(postService.deletePostByPostId(postId), HttpStatus.OK);

	}

}
