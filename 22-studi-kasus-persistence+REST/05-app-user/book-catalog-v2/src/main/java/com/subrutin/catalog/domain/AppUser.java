package com.subrutin.catalog.domain;

import java.util.UUID;

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
@Data
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "secure_id", nullable = false, unique = true)
	private UUID secureId = Generators.timeBasedEpochGenerator().generate();
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;;
	
	@Column(name = "mobile_number", nullable = false)
	private String mobileNumber;
	
	@Column(name = "deleted", columnDefinition = "boolean default false not null")
	private Boolean deleted;
	
	@PrePersist
	public void prePersist() {
		this.deleted=false;
	}

}
