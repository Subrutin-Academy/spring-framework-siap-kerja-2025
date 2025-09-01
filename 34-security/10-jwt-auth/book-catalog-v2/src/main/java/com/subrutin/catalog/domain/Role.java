package com.subrutin.catalog.domain;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority {
	
	@Id
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public String getAuthority() {
		return "ROLE_"+this.code;
	}

}
