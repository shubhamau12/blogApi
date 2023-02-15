package com.codewithshubham.blog.services.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithshubham.blog.Payloads.CategoryDto;
import com.codewithshubham.blog.entities.Category;
import com.codewithshubham.blog.exceptions.ResourceNotFoundException;
import com.codewithshubham.blog.repositories.CategoryRepo;
import com.codewithshubham.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired 
	private CategoryRepo categoryRepo;
	
	@Autowired 
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat =this.modelMapper.map(categoryDto,Category.class);
		Category  addedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","CategoryId", categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDesc(categoryDto.getCategoryDesc());
		Category updatedCat=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCat,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer catID) {
		Category cat = this.categoryRepo.findById(catID).orElseThrow(()->new ResourceNotFoundException("category","CategoryId", catID));
        this.categoryRepo.delete(cat);		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","CategoryId", categoryId));
		return this.modelMapper.map(cat,CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories=this.categoryRepo.findAll();
	List<CategoryDto> catDtos	=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());		
		return catDtos;
	}
	

}
