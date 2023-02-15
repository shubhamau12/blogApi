package com.codewithshubham.blog.services;

import java.util.List;

import com.codewithshubham.blog.Payloads.PostDto;
import com.codewithshubham.blog.entities.Post;

public interface PostService {
	
	// create 
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	// update
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	// delete
	
	void deletePost(Integer postId);
	
	// get All posts
	
	List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	
	// get single Post
	
	PostDto getPostById(Integer postId);
	
	// get All post by category
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	// get all post by user
	
	List<PostDto> getPostByUser(Integer userId);
	
	// serach post
	List<PostDto> serachPost(String keyword);

}
