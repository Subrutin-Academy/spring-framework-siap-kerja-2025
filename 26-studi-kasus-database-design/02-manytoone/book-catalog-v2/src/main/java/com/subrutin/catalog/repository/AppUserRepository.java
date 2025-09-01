package com.subrutin.catalog.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	//select au from app_user au where UPPER(au.name) LIKE UPPER("Tedy");
	public Page<AppUser> findAllByNameLikeIgnoreCase(String name, Pageable pageable);
	
	public Optional<AppUser> findBySecureId(UUID secureId);

}
