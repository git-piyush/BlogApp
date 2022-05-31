package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.entity.Post;

//No need to annotate here with @Repository because inside JpaRepository there is class SimpleJpaRepository which is annotated with @Repository
@Repository		//@Repository Annotation is a specialization of @Component annotation which is used to indicate that the class provides the mechanism for storage, retrieval, update, delete and search operation on objects
public interface PostRepository extends JpaRepository<Post, Long>{

}
