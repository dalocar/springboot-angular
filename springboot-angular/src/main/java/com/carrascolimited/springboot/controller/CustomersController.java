package com.carrascolimited.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carrascolimited.springboot.service.CustomerService;
import com.carrascolimited.springboot.vo.CustomerListVO;
import com.carrascolimited.springboot.vo.CustomerLiteVO;
import com.carrascolimited.springboot.vo.CustomerVO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomersController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerController customerController;

	@RequestMapping("{id}")
	public CustomerController getCustomerController() {
		return customerController;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CustomerVO getUser(@PathVariable("id") Integer id) {
		return customerService.getCustomerById(id, CustomerVO.class);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "page", "size", "sortColumn", "sortDirection" })
	public CustomerListVO<CustomerVO> getCustomers(Integer page, Integer size, String sortColumn,
			String sortDirection) {
		return customerService.findCustomers(page, size, sortColumn, sortDirection, CustomerVO.class);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/all", params = { "filter" })
	public List<CustomerLiteVO> getAllCustomers(String filter) {
		return customerService.findFiltered(filter, CustomerLiteVO.class);
	}
}