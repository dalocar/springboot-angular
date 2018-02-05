package com.carrascolimited.springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carrascolimited.springboot.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	Invoice findByIdAndYear(Integer id, Integer year);

	@Query("select distinct year from Invoice invoice where invoice.customer.id = ?1 order by year desc")
	List<Integer> findInvoiceYearsByCustomerId(Integer customerId);
	
	List<Invoice> findByCustomerIdAndYear(Integer customerId, Integer Year);
}
