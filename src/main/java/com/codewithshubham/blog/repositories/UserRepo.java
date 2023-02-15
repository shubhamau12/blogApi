package com.codewithshubham.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithshubham.blog.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
