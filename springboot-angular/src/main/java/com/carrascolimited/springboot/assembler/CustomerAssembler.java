package com.carrascolimited.springboot.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carrascolimited.springboot.domain.Customer;
import com.carrascolimited.springboot.service.DozerService;
import com.carrascolimited.springboot.vo.CreateCustomerVO;
import com.carrascolimited.springboot.vo.CustomerVO;

import lombok.Data;

@Component
@Data
public class CustomerAssembler {

	@Autowired
	private DozerService dozerService;	

	/**
	 * CreateUserVO convert to User.
	 *
	 * @param createUserVO
	 * @return
	 */
	public Customer toCustomer(CreateCustomerVO createUserVO) {		
		return dozerService.map(createUserVO, Customer.class);
	}

	/**
	 * User to UserVO.
	 *
	 * @param user
	 * @return
	 */
	public CustomerVO toCustomerVO(Customer customer) {
		return dozerService.map(customer, CustomerVO.class);
	}

}