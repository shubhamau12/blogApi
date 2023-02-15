package com.codewithshubham.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	long fieldVAlue;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldVAlue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName, fieldVAlue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldVAlue = fieldVAlue;
	}
	

	
}
