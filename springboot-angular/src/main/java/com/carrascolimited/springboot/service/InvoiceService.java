package com.carrascolimited.springboot.service;

import com.carrascolimited.springboot.vo.InvoiceVO;

public interface InvoiceService {

	InvoiceVO getByInvoiceId(Integer year, Integer id);

}
