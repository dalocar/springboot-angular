package com.carrascolimited.springboot.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class InvoiceLineId implements Serializable {
	
	private static final long serialVersionUID = 6173958336167441239L;

	private Integer lineNumber;
	private Integer invoiceId;
	private Integer invoiceYear;

}
