package com.carrascolimited.springboot.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames="email"), 
		@UniqueConstraint(columnNames = "username")})
@Data
@Accessors(chain = true)
public class User implements Serializable {

	private static final long serialVersionUID = -5042511631728910650L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "created")
	@CreationTimestamp
	private Date created;

	@Column(name = "updated")
	@UpdateTimestamp
	private Date updated;

	@Column(name = "password")
	private String password;

}