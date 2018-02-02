package com.carrascolimited.springboot.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerListVO {

	private Long total;
	private List<CustomerVO> customers = new ArrayList<>();

}