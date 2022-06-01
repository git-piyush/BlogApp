package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.DTO.PostRequestDto;
import com.blog.service.PostService;

/*@RestController is a convenience annotation for creating Restful controllers.
 * This mark controller classes as a request handler to allow Spring to recognize
 * it as a RESTful service during runtime.
 * 
 * @Controller + @ResponseBody
 */
@RestController
@RequestMapping("/api/post")
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
		 * custom parser etc.
		 * // http message converter
		 * @RequestBody annotation maps the HttpRequest body to a transfer or domain object,
		 *  enabling automatic deserialization of the inbound HttpRequest body onto a Java object.
		 */
		
		/*ResponseEntity represents the whole HTTP response: status code, headers, and body.
		 * As a result, we can use it to fully configure the HTTP response.
		 */
		return new ResponseEntity<PostRequestDto>(postService.createPost(postRequestDto), HttpStatus.CREATED);

	}

}
