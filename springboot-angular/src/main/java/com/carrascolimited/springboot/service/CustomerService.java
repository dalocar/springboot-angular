package com.carrascolimited.springboot.service;

import java.util.List;

import com.carrascolimited.springboot.vo.CreateCustomerVO;
import com.carrascolimited.springboot.vo.CustomerListVO;
import com.carrascolimited.springboot.vo.CustomerVO;

public interface CustomerService {

	<T> T getCustomerById(Integer id, Class<T> targetVO);

	<T> T createCustomer(CreateCustomerVO user, Class<T> targetVO);

	<T> T updateCustomer(CustomerVO user, Class<T> targetVO);

	void deleteUser(Integer id);

	<T> List<T> findAll(Class<T> targetVO);

	<T> CustomerListVO<T> findCustomers(int page, int size, String sortColumn, String sortDirection, Class<T> targetVO);

	<T> List<T> findFiltered(String filter, Class<T> targetVO);

}