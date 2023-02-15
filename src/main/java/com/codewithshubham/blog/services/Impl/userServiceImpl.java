package com.codewithshubham.blog.services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import com.codewithshubham.blog.exceptions.ResourceNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithshubham.blog.Payloads.userDto;
import com.codewithshubham.blog.entities.User;
import com.codewithshubham.blog.repositories.UserRepo;
import com.codewithshubham.blog.services.UserService;

@Service
public class userServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public userDto createUser(userDto UserDto) {
		
		User user= this.dtoToEntity(UserDto);
	User saveUser=	this.userRepo.save(user);
		return this.EntityToDto(saveUser);
	}

	@Override
	public userDto updateUser(userDto UserDto, Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","ID",userId));
		user.setName(UserDto.getName());
		user.setEmail(UserDto.getEmail());
		user.setPassword(UserDto.getPassword());
		user.setAbout(UserDto.getAbout());
		User updUser=this.userRepo.save(user);
		userDto UD= this.EntityToDto(updUser);
		return UD;
	}

	@Override
	public userDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","ID",userId));
		return this.EntityToDto(user);
	}

	@Override
	public List<userDto> getAllUsers() {
	
		List<User> users=this.userRepo.findAll();
		List<userDto> UserDtos=users.stream().map(user->this.EntityToDto(user)).collect(Collectors.toList());
		return UserDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","ID",userId));
		this.userRepo.delete(user);

	}

	public User dtoToEntity(userDto UserDto) {
		User user= this.modelMapper.map(UserDto,User.class);
//		user.setId(UserDto.getId());
//		user.setName(UserDto.getName());
//		user.setEmail(UserDto.getEmail());
//		user.setPassword(UserDto.getPassword());
//		user.setAbout(UserDto.getAbout());
		return user;
		
	}
	
	public userDto EntityToDto(User user)
	{
		userDto UserDto = this.modelMapper.map(user, userDto.class);
//		UserDto.setId(user.getId());
//		UserDto.setName(user.getName());
//		UserDto.setEmail(user.getEmail());
//		UserDto.setPassword(user.getPassword());
//		UserDto.setAbout(user.getAbout());		
		return UserDto;
	 	
	}
	
}
