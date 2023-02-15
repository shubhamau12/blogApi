package com.codewithshubham.blog.Payloads;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min=4)
    private String categoryTitle;
	
	@NotEmpty
	@Size(min=10)
	private String categoryDesc;


}
