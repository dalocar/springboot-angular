package com.carrascolimited.springboot.service;

import java.util.List;

import com.carrascolimited.springboot.vo.CreateCustomerVO;
import com.carrascolimited.springboot.vo.CustomerListVO;
import com.carrascolimited.springboot.vo.CustomerVO;

public interface CustomerService {

	CustomerVO getCustomerById(Integer id);

	CustomerVO createCustomer(CreateCustomerVO user);

	CustomerVO updateCustomer(CustomerVO user);

	void deleteUser(Integer id);

	List<CustomerVO> findAll();	

	CustomerListVO findCustomers(int page, int size, String sortColumn, String sortDirection);

	List<CustomerVO> findFiltered(String filter);

}