package com.subrutin.catalog.web;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.subrutin.catalog.dto.AppUserDetailResponseDTO;
import com.subrutin.catalog.dto.AppUserListResponseDTO;
import com.subrutin.catalog.dto.AppUserRequestDTO;
import com.subrutin.catalog.dto.ResultPageResponseDTO;
import com.subrutin.catalog.service.AppUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/app-user")
public class AppUserResource {

	private final AppUserService appUserService;

	@PostMapping
	public ResponseEntity<Void> createdAppUser(@RequestBody @Valid AppUserRequestDTO dto) {
		appUserService.createAppUser(dto);
		return ResponseEntity.created(URI.create("/v1/app-user")).build();
	}

	@GetMapping
	public ResponseEntity<ResultPageResponseDTO<AppUserListResponseDTO>> findAppUserList(
			@RequestParam(defaultValue = "0") Integer pages, 
			@RequestParam(defaultValue = "10") Integer limit,
			@RequestParam(defaultValue = "asc") String direction, 
			@RequestParam(defaultValue = "id") String sortBy, 
			@RequestParam(required = false) String name) {
		return ResponseEntity.ok(appUserService.findAppUserList(pages, limit, direction, sortBy, name));

	}
	
	//v1/app-user/{id}
	@GetMapping("{id}")
	public ResponseEntity<AppUserDetailResponseDTO> findAppUserDetail(@PathVariable UUID id) {
		return ResponseEntity.ok(appUserService.findAppUserDetail(id));
	}

}
