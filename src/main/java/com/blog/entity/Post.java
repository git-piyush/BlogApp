package com.blog.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data						//@Data is a lombak annotation which provied getter and setter functionality internally
@AllArgsConstructor
@NoArgsConstructor
@Entity						//@Entity is JPA annotation which represent a JPA entity
@Table(name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = { "postTitle" })})	//@Table is a hibernate way to customise table name and mention contrains if any, and the fields declared in this is going to map in db in future
public class Post {

	@Id						//@Id Identifiers in Hibernate represent the primary key of an entity
	@GeneratedValue(strategy = GenerationType.IDENTITY)		//@GeneratedValue annotation specifies how to generate values for the given column
	@Column(name="postId")			//@Column is used to customize the name of the column in database
	private Long id;
	
	@Column(name="postTitle")	
	private String title;
	
	@Column(name="postDescription")
	private String description;
	
	@Column(name="postContent")
	private String content;
	
	//@OneToMany annotation is used to create one to many relationship between two diffrent entity
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Comment> comments = new HashSet<>();
	
}
