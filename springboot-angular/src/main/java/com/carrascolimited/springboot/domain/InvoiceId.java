package com.carrascolimited.springboot.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class InvoiceId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer year;
}
