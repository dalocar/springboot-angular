package com.carrascolimited.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.carrascolimited.springboot.assembler.CustomerAssembler;
import com.carrascolimited.springboot.domain.Customer;
import com.carrascolimited.springboot.exception.ResourceNotFoundException;
import com.carrascolimited.springboot.repo.CustomerRepository;
import com.carrascolimited.springboot.vo.CreateCustomerVO;
import com.carrascolimited.springboot.vo.CustomerListVO;
import com.carrascolimited.springboot.vo.CustomerVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerAssembler customerAssembler;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public <T> T getCustomerById(Integer id, Class<T> targetVO) {
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			throw new ResourceNotFoundException(id, "customer not found");
		}
		return customerAssembler.toCustomerVO(customer, targetVO);
	}

	@Override
	public <T> T createCustomer(CreateCustomerVO createCustomer, Class<T> targetVO) {
		Customer customer = customerAssembler.toCustomer(createCustomer);
		return customerAssembler.toCustomerVO(customerRepository.save(customer), targetVO);
	}

	@Override
	public <T> T updateCustomer(CustomerVO updateCustomer, Class<T> targetVO) {
		Customer customer = customerRepository.getOne(updateCustomer.getId());
		try {
			customer.setAddress(updateCustomer.getAddress());
			customer.setCif(updateCustomer.getCif());
			customer.setCity(updateCustomer.getCity());
			customer.setFax(updateCustomer.getFax());
			customer.setName(updateCustomer.getName());
			customer.setPhone(updateCustomer.getPhone());
			customer.setPostCode(updateCustomer.getPostCode());
			customer.setProvince(updateCustomer.getProvince());
			customer.setShowInList(updateCustomer.isShowInList());
		} catch (Exception e) {
			log.error("Illegal Access exception", e);
		}
		return customerAssembler.toCustomerVO(customerRepository.save(customer), targetVO);
	}

	public void deleteUser(Integer id) {
		Customer customer = customerRepository.getOne(id);
		if (customer != null) {
			customer.setActive(false);
			customerRepository.save(customer);
		}
	}

	@Override
	public <T> List<T> findAll(Class<T> targetVO) {
		List<T> result = new ArrayList<>();
		customerRepository.findAll().forEach(u -> result.add(customerAssembler.toCustomerVO(u, targetVO)));
		return result;
	}

	@Override
	public <T> CustomerListVO findCustomers(int page, int size, String sortColumn, String sortDirection,
			Class<T> targetVO) {
		CustomerListVO customerListVO = new CustomerListVO();
		List<T> result = new ArrayList<>();
		Direction direction = Direction.ASC;
		if (sortDirection.toLowerCase().equals("desc")) {
			direction = Direction.DESC;
		}
		Page<Customer> activeCustomers = customerRepository
				.findByActiveTrue(new PageRequest(page, size, direction, sortColumn));
		activeCustomers.forEach(u -> result.add(customerAssembler.toCustomerVO(u, targetVO)));
		customerListVO.setCustomers(result);
		customerListVO.setTotal(activeCustomers.getTotalElements());
		return customerListVO;
	}

	@Override
	public <T> List<T> findFiltered(String filter, Class<T> targetVO) {
		List<T> result = new ArrayList<>();
		customerRepository.findByNameContainingOrAddressContainingAllIgnoreCaseOrderByNameAsc(filter, filter)
				.forEach(u -> result.add(customerAssembler.toCustomerVO(u, targetVO)));
		return result;
	}
}