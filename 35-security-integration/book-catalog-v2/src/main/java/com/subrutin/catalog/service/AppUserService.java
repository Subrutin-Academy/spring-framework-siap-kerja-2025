package com.subrutin.catalog.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.subrutin.catalog.dto.AppUserDetailResponseDTO;
import com.subrutin.catalog.dto.AppUserListResponseDTO;
import com.subrutin.catalog.dto.AppUserRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;

public interface AppUserService extends UserDetailsService {
	
	public void createAppUser(AppUserRequestDTO dto);
	
	public ResultPageResponseDTO<AppUserListResponseDTO> findAppUserList(
			Integer pages,
			Integer limit,
			String direction,
			String sortBy,
			String name
			);
	
	public AppUserDetailResponseDTO findAppUserDetail(UUID id);
	
	public void updateAppUser(UUID id, AppUserRequestDTO dto);
	
	public void deleteAppUser(UUID id);
	
	public void hardDeleteAppUser(UUID id);

}
