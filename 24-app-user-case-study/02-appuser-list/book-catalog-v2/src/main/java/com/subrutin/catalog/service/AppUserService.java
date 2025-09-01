package com.subrutin.catalog.service;

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

}
