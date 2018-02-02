package com.carrascolimited.springboot.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrascolimited.springboot.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Page<Customer> findByActiveTrue(Pageable pageabke);
	
	List<Customer> findByNameContainingOrAddressContainingAllIgnoreCase(String name, String address);
}
