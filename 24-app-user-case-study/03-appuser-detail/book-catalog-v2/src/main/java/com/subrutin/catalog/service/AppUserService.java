package com.subrutin.catalog.service;

import java.util.UUID;

import com.subrutin.catalog.dto.AppUserDetailResponseDTO;
import com.subrutin.catalog.dto.AppUserListResponseDTO;
import com.subrutin.catalog.dto.AppUserRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;

public interface AppUserService {
	
	public void createAppUser(AppUserRequestDTO dto);
	
	public ResultPageResponseDTO<AppUserListResponseDTO> findAppUserList(
			Integer pages,
			Integer limit,
			String direction,
			String sortBy,
			String name
			);
	
	public AppUserDetailResponseDTO findAppUserDetail(UUID id);

}
