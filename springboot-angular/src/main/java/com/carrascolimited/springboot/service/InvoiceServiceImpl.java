package com.carrascolimited.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrascolimited.springboot.repo.InvoiceRepository;
import com.carrascolimited.springboot.vo.InvoiceVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository repository;

	@Autowired
	private DozerService dozerService;

	@Override
	public InvoiceVO getByInvoiceId(Integer year, Integer id) {
		return dozerService.map(repository.findByIdAndYear(id, year), InvoiceVO.class);
	}

}
