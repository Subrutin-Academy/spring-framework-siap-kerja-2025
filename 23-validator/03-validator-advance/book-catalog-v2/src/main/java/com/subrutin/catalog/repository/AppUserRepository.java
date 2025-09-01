package com.subrutin.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subrutin.catalog.domain.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
