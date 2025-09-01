package com.subrutin.catalog.domain;

import java.util.UUID;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.uuid.Generators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "app_user")
@SQLRestriction("deleted = false")
@Data
public class AppUser extends AbstractBaseUUIDEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;
	

}
