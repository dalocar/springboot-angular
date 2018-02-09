package com.carrascolimited.springboot.vo;

import javax.validation.constraints.NotNull;

import com.carrascolimited.springboot.util.validator.Username;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateUserVO {
	@NotNull(message = "first name can not be null.")
	private String firstName;
	@NotNull(message = "last name can not be null.")
	private String lastName;
	@NotNull(message = "email can not be null.")
	private String email;
	@NotNull(message = "password can not be null.")
	private String password;
	
	@Username(message = "invalid username.")
	private String userName;
}