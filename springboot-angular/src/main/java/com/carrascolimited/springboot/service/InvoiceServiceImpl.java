package com.carrascolimited.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.carrascolimited.springboot.domain.Customer;
import com.carrascolimited.springboot.domain.Invoice;
import com.carrascolimited.springboot.repo.InvoiceRepository;
import com.carrascolimited.springboot.vo.InvoiceListVO;
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

	@Override
	public List<Integer> getInvoiceYears(Integer customerId) {
		return repository.findInvoiceYearsByCustomerId(customerId);
	}

	@Override
	public InvoiceListVO getInvoicesYear(Integer customerId, Integer year, Integer page, Integer size) {

		Page<Invoice> activeCustomers = repository.findByCustomerIdAndYear(customerId, year,
				new PageRequest(page, size));

		InvoiceListVO listVo = new InvoiceListVO();
		listVo.setInvoices(dozerService.mapList(activeCustomers.getContent(), InvoiceVO.class));
		listVo.setTotal(activeCustomers.getTotalElements());
		return listVo;
	}

}
