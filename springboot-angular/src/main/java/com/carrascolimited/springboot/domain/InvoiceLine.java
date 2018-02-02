package com.carrascolimited.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity(name = "InvoiceLine")
@Table(name = "Linea_Factura")
@Data
@Accessors(chain = true)
@IdClass(InvoiceLineId.class)
public class InvoiceLine {

	@Id
	@Column(name = "numero_linea")
	private Integer lineNumber;

	@Id
	@Column(name = "numero_factura")
	private Integer invoiceId;

	@Id
	@Column(name = "any_factura")
	private Integer invoiceYear;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns(foreignKey = @ForeignKey(name = "fk_line_invoice_invoice_1"), value = {
			@JoinColumn(name = "numero_factura", referencedColumnName = "numero", insertable = false, updatable = false),
			@JoinColumn(name = "any_factura", referencedColumnName = "anyo", insertable = false, updatable = false) })
	private Invoice invoice;

	@Column(name = "cantidad")
	private Double amount;
	@Column(name = "description")
	private String description;
	@Column(name = "precio")
	private Double price;
	@Column(name = "iva")
	private Double vat;

}
