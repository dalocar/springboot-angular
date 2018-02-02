package com.carrascolimited.springboot.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InvoiceLineVO {
	
	private Integer lineNumber;
	private Integer invoiceId;
	private Integer invoiceYear;
	private Double amount;
	private String description;
	private Double price;
	private Double vat;

}