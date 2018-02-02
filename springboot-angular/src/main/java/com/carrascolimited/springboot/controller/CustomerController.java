package com.carrascolimited.springboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carrascolimited.springboot.service.CustomerService;
import com.carrascolimited.springboot.vo.CreateCustomerVO;
import com.carrascolimited.springboot.vo.CustomerListVO;
import com.carrascolimited.springboot.vo.CustomerVO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CustomerVO getUser(@PathVariable("id") Integer id) {
		return customerService.getCustomerById(id);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "page", "size", "sortColumn", "sortDirection" })
	public CustomerListVO getCustomers(Integer page, Integer size, String sortColumn, String sortDirection) {
		return customerService.findCustomers(page, size, sortColumn, sortDirection);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/all", params = { "filter" })
	public List<CustomerVO> getAllCustomers(String filter) {
		return customerService.findFiltered(filter);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CustomerVO createUser(@Valid @RequestBody CreateCustomerVO userVO) {
		return customerService.createCustomer(userVO);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public CustomerVO updateUser(@RequestBody CustomerVO updateUserVO) {
		return customerService.updateCustomer(updateUserVO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		customerService.deleteUser(id);
		return "OK";
	}
}