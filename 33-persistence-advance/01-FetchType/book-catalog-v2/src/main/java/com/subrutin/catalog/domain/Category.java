package com.subrutin.catalog.domain;

import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "category")
@SQLRestriction("deleted = false")
@Data
public class Category extends AbstractBaseEntity {
	
	@Id
	private String code;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@ManyToMany(mappedBy = "categories")
	private Set<Book> books;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(code, other.code) && Objects.equals(description, other.description)
				&& Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(code, description, name);
		return result;
	}
	
	

}
