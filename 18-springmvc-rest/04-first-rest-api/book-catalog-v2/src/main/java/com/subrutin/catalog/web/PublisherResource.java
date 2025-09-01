package com.subrutin.catalog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1/publishers")
public class PublisherResource {
	
	@GetMapping //GET /v1/publishers
	@ResponseBody
	public String hello() {
		return "This is my first REST API";
	}

}
