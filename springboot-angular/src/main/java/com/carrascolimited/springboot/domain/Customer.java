package com.carrascolimited.springboot.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity(name = "Customer")
@Table(name = "Clientes")
@Data
@Accessors(chain = true)
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Identificador")
	private Integer id;

	@Column(name = "Nombre")
	private String name;

	@Column(name = "Direccion")
	private String address;

	@Column(name = "CP")
	private Integer postCode;

	@Column(name = "Poblacion")
	private String city;

	@Column(name = "Provincia")
	private String province;

	@Column(name = "CIF")
	private String cif;

	@Column(name = "Telefono")
	private String phone;

	@Column(name = "Fax")
	private String fax;

	@Column(name = "Facturas")
	private boolean showInList;

	@Column(name = "activo")
	private boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@LazyCollection(LazyCollectionOption.EXTRA)
	private List<Invoice> invoices = new ArrayList<>();

}
