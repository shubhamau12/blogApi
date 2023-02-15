package com.codewithshubham.blog.services;

import java.util.List;

import com.codewithshubham.blog.Payloads.CategoryDto;

public interface CategoryService {
	
	// Create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	// Update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	// delete
	public void deleteCategory(Integer catID);
	
	// get
	public CategoryDto getCategory(Integer categoryId);
		
	//get All
	List<CategoryDto> getCategories();

}
