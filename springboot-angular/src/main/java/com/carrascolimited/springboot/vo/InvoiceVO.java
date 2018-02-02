package com.carrascolimited.springboot.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InvoiceVO {
	private Integer id;
	private Integer year;
	private Integer date;
	private Double irpf;
	private List<InvoiceLineVO> lines = new ArrayList<>();
}