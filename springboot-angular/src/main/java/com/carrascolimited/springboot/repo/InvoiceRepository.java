package com.carrascolimited.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carrascolimited.springboot.domain.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	Invoice findByIdAndYear(Integer id, Integer year);

}
