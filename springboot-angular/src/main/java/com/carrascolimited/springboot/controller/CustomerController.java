package com.carrascolimited.springboot.controller;

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
import com.carrascolimited.springboot.vo.CustomerVO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public CustomerVO getUser(@PathVariable("id") Integer id) {
		return customerService.getCustomerById(id, CustomerVO.class);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CustomerVO createUser(@Valid @RequestBody CreateCustomerVO userVO) {
		return customerService.createCustomer(userVO, CustomerVO.class);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public CustomerVO updateUser(@RequestBody CustomerVO updateUserVO) {
		return customerService.updateCustomer(updateUserVO, CustomerVO.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Integer id) {
		customerService.deleteUser(id);
		return "OK";
	}
}