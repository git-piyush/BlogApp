package com.blog.DTO;

import lombok.Data;

@Data
public class PostRequestDto {

	private Long id;

	private String title;

	private String description;

	private String content;

}
