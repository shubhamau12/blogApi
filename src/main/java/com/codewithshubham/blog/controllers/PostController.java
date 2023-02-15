package com.codewithshubham.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.codewithshubham.blog.Payloads.ApiResponse;
import com.codewithshubham.blog.Payloads.PostDto;
import com.codewithshubham.blog.Payloads.userDto;
import com.codewithshubham.blog.entities.Post;
import com.codewithshubham.blog.repositories.PostRepo;
import com.codewithshubham.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private PostService postService;

	// create
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
		PostDto createdPost=this.postService.createPost(postDto,userId,categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
		
	}
	
	// get By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer userId){
		
		List<PostDto> posts=this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		
	}
	
	// get by Category
	// get By User
		@GetMapping("/category/{categoryId}/posts")
		public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer categoryId){
			
			List<PostDto> posts=this.postService.getPostByCategory(categoryId);
			return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
			
		}
		// get all posts
		
		@GetMapping("/posts")
		public ResponseEntity<List<PostDto>> getAllPost(
				@RequestParam(value="pageNumber", defaultValue="1", required=false) Integer pageNumber,
				@RequestParam(value="pageSize", defaultValue="5", required=false) Integer pageSize
				){
			return ResponseEntity.ok(this.postService.getAllPost(pageNumber,pageSize));
			
		}
		
		// get Post Id
		
		@GetMapping("/posts/{postId}")
		public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
			return ResponseEntity.ok(this.postService.getPostById(postId));
			
		}
		
		// delete post
		
		@DeleteMapping("/posts/{postId}")
		public ApiResponse deletePost(@PathVariable("postId") Integer postId){
			this.postService.deletePost(postId);
			return new ApiResponse("Post Successfully deleted", true);
						
		}
		
		// update Post
		@PutMapping("/posts/{postId}")
		public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId){
			PostDto updatePost=this.postService.updatePost(postDto, postId);
			return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
									
		}
	
}
