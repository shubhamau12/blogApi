package com.codewithshubham.blog.services;

import java.util.List;

import com.codewithshubham.blog.Payloads.userDto;
import com.codewithshubham.blog.entities.User;

public interface UserService {

	userDto createUser(userDto user);
	userDto updateUser(userDto user, Integer userId);
	userDto getUserById(Integer userId);
	List<userDto> getAllUsers();
	void deleteUser(Integer userId);
}
