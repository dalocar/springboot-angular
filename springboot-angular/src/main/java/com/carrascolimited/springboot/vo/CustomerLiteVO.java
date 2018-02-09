package com.carrascolimited.springboot.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerLiteVO {
	private Integer id;
	private String name;
	private String address;	

}