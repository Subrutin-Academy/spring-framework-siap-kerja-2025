package com.subrutin.catalog.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.subrutin.catalog.domain.AppUser;
import com.subrutin.catalog.dto.AppUserDetailResponseDTO;
import com.subrutin.catalog.dto.AppUserListResponseDTO;
import com.subrutin.catalog.dto.AppUserRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.repository.AppUserRepository;
import com.subrutin.catalog.service.AppUserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {
	 
	private final AppUserRepository appUserRepository;

	@Override
	public void createAppUser(AppUserRequestDTO dto) {
		//dto -> appUser
		AppUser appUser = new AppUser();
		appUser.setName(dto.name());
		appUser.setEmail(dto.email());
		appUser.setMobileNumber(dto.mobileNumber());
		//appuserrepository
		appUserRepository.save(appUser);
	}

	@Override
	public ResultPageResponseDTO<AppUserListResponseDTO> findAppUserList(Integer pages, Integer limit, String direction,
			String sortBy,
			String name) {
		name = StringUtils.isBlank(name)?"%":name+"%";
		Sort sort = Sort.by(new Sort.Order(Direction.valueOf(direction.toUpperCase()), sortBy));
		Pageable pageable =PageRequest.of(pages, limit, sort);
		Page<AppUser> resultPage =  appUserRepository.findAllByNameLikeIgnoreCase(name, pageable);
		List<AppUserListResponseDTO> resultDTO= resultPage.stream().map((a)->{
			return new AppUserListResponseDTO(a.getSecureId(), a.getName());
		}).collect(Collectors.toList());
		return new ResultPageResponseDTO<>(resultDTO, resultPage.getTotalPages(), resultPage.getTotalElements());
	}

	@Override
	public AppUserDetailResponseDTO findAppUserDetail(UUID id) {
		// 1. repository layer db -> entity
		AppUser appUser =  appUserRepository.findBySecureId(id)
			.orElseThrow(()->new RuntimeException("app user id not found"));
		// 2. entity -> dto
		return new AppUserDetailResponseDTO(appUser.getSecureId(), 
				appUser.getName(), appUser.getEmail(), appUser.getMobileNumber());
	}

	@Transactional
	@Override
	public void updateAppUser(UUID id, AppUserRequestDTO dto) {
		AppUser appUser =  appUserRepository.findBySecureId(id)
				.orElseThrow(()->new RuntimeException("app user id not found"));	
		appUser.setName(dto.name());
		appUser.setEmail(dto.email());
		appUser.setMobileNumber(dto.mobileNumber());
		//appuserrepository
		appUserRepository.save(appUser); 
	}

	@Transactional
	@Override
	public void deleteAppUser(UUID id) {
		AppUser appUser =  appUserRepository.findBySecureId(id)
				.orElseThrow(()->new RuntimeException("app user id not found"));
		appUser.setDeleted(true);
		appUserRepository.save(appUser);
	}

}
