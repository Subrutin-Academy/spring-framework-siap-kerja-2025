package com.subrutin.catalog.domain;

import java.util.UUID;

import com.fasterxml.uuid.Generators;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractBaseUUIDEntity extends AbstractBaseEntity {

	@Column(name = "secure_id", nullable = false, unique = true)
	private UUID secureId;
	
	@Override
	@PrePersist
	public void prePersist() {
		setDeleted(false);
		if(this.secureId==null) {
			secureId=Generators.timeBasedEpochGenerator().generate();
		}
		
	}
}
