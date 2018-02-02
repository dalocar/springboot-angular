package com.carrascolimited.springboot.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity(name = "Invoice")
@Table(name = "Factura")
@Data
@Accessors(chain = true)
@IdClass(InvoiceId.class)
public class Invoice implements Serializable {
	
	private static final long serialVersionUID = -7145050076063170323L;

	@Id
	@Column(name = "numero")
	private Integer id;
	@Id
	@Column(name = "anyo")
	private Integer year;

	@Column(name = "fecha")
	private LocalDate date;

	@Column(name = "irpf")
	private Double irpf;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente", referencedColumnName = "Identificador", foreignKey = @ForeignKey(name = "fk_invoice_customer_1"))
	private Customer customer;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")	
	private List<InvoiceLine> lines = new ArrayList<>(); 
}
