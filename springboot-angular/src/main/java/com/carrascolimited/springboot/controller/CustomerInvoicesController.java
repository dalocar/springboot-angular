package com.carrascolimited.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carrascolimited.springboot.service.InvoiceService;
import com.carrascolimited.springboot.vo.InvoiceListVO;

@RestController
@RequestMapping("/customer/{customerId}/invoices")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerInvoicesController {

	@Autowired
	private InvoiceService invoiceService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Integer> getUserInvoices(@PathVariable Integer customerId) {
		return invoiceService.getInvoiceYears(customerId);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{year}", params = { "page", "size" })
	public InvoiceListVO getUserInvoices(@PathVariable Integer customerId, @PathVariable Integer year, Integer page,
			Integer size) {
		return invoiceService.getInvoicesYear(customerId, year, page, size);
	}

}
