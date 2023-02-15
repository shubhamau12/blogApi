package com.codewithshubham.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithshubham.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
