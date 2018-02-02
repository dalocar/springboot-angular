package com.carrascolimited.springboot.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateCustomerVO {
	private String name;
	private String address;
	private Integer postCode;
	private String city;
	private String province;
	private String cif;
	private String phone;
	private String fax;
	private boolean showInList;
	private boolean active;

}