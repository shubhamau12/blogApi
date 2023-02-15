package com.codewithshubham.blog.controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.codewithshubham.blog.Payloads.ApiResponse;
import com.codewithshubham.blog.Payloads.userDto;
import com.codewithshubham.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	// POST-create user
	
	@PostMapping("/")
	public ResponseEntity<userDto> createUser(@Valid @RequestBody userDto UserDto){
		
		userDto createdUserDto=this.userService.createUser(UserDto);
		return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
		
	}
	
	//PUT- update user
	@PutMapping("/{userId}")
	public ResponseEntity<userDto> updateUser(@Valid @RequestBody userDto UserDto, @PathVariable("userId") Integer userId){
		userDto updatedUser=this.userService.updateUser(UserDto, userId);
		return ResponseEntity.ok(updatedUser);
		
	}
	
	// Delete - delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
				
		
	}
	// Get - get user
	
	@GetMapping("/")
	public ResponseEntity<List<userDto>> getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUsers());
		
	}
	
	// Get - get user
	
		@GetMapping("/{userId}")
		public ResponseEntity<userDto> getSingleUser(@PathVariable("userId") Integer userId){
			return ResponseEntity.ok(this.userService.getUserById(userId));
			
		}
}
