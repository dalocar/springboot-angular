package com.carrascolimited.springboot.service;

import java.util.List;

import com.carrascolimited.springboot.vo.InvoiceVO;

public interface InvoiceService {

	InvoiceVO getByInvoiceId(Integer year, Integer id);

	List<Integer> getInvoiceYears(Integer customerId);

	List<InvoiceVO> getInvoicesYear(Integer customerId, Integer year);

}
