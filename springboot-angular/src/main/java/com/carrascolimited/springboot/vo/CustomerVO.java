package com.carrascolimited.springboot.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class CustomerVO extends CreateCustomerVO {
	private Integer id;
	private List<InvoiceVO> invoices = new ArrayList<>();

}