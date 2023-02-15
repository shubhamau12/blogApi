package com.codewithshubham.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithshubham.blog.entities.Category;
import com.codewithshubham.blog.entities.Post;
import com.codewithshubham.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

}
