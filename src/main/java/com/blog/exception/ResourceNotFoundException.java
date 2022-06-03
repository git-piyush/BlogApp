package com.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)		//ResponseStatus annotation cause spring boot to response with the specified HTTP status code whenever this exception is thrown from controller
public class ResourceNotFoundException extends RuntimeException{

	private String resourceName;
	
	private String fieldName;
	
	private Long fieldValue;

	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));	//Post not found with Id: 1
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
