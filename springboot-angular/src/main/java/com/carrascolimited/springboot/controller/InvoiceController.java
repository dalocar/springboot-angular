package com.carrascolimited.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carrascolimited.springboot.service.InvoiceServiceImpl;
import com.carrascolimited.springboot.vo.InvoiceVO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceServiceImpl service;

	
	@RequestMapping(value = "/{year}/{id}", method = RequestMethod.GET)
	public InvoiceVO getUser(@PathVariable("year") Integer year, @PathVariable("id") Integer id) {
		return service.getByInvoiceId(year, id);
	}
}