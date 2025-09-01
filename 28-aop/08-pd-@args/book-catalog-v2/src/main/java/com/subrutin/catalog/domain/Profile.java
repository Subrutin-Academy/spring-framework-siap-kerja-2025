package com.subrutin.catalog.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "profile")
@Data
public class Profile extends AbstractBaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "birth_date")
	private LocalDate birthDate;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private AppUser appUser;

}
