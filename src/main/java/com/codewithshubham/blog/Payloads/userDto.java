package com.codewithshubham.blog.Payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class userDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message="Username must be min of 4 character")
	private String name;
	
	@Email(message="Email Address is incorrect")
	private String email;
	@NotEmpty
	@Size(min=4,max=10, message="Passowrd must be min of 4 character and max of 10 char")
	private String password;
	@NotNull
	private String about;
	

}
