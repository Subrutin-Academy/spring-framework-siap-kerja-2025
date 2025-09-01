package com.subrutin.catalog.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractBaseEntity {

	@Column(name = "deleted", columnDefinition = "boolean default false not null")
	private Boolean deleted;
	
	@PrePersist
	public void prePersist() {
		this.deleted=false;
	}
}
