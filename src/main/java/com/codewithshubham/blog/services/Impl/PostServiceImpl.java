package com.codewithshubham.blog.services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codewithshubham.blog.Payloads.PostDto;
import com.codewithshubham.blog.entities.Category;
import com.codewithshubham.blog.entities.Post;
import com.codewithshubham.blog.entities.User;
import com.codewithshubham.blog.exceptions.ResourceNotFoundException;
import com.codewithshubham.blog.repositories.CategoryRepo;
import com.codewithshubham.blog.repositories.PostRepo;
import com.codewithshubham.blog.repositories.UserRepo;
import com.codewithshubham.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","ID",userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","CategoryId", categoryId));
		Post post=this.modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		System.out.println("date is"+new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost=this.postRepo.save(post);
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
		
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost= this.postRepo.findAll(p);
		List<Post> allPost=pagePost.getContent();
		List<PostDto> postDto=allPost.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());		
		return postDto;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));	
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","ID",categoryId));
	    List<Post> posts=this.postRepo.findByCategory(cat);
	   List<PostDto> postDtos =posts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","ID",userId));
		List<Post> posts=this.postRepo.findByUser(user);
		 List<PostDto> postDtos =posts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> serachPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
