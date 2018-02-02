package com.carrascolimited.springboot.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserVO {
	private Long id;
	private String fullName;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
}