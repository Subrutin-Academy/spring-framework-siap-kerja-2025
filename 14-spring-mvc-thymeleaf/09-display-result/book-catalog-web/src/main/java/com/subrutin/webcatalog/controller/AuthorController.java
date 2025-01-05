package com.subrutin.webcatalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.subrutin.webcatalog.dto.AuthorCreateRequestDTO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("author")
public class AuthorController {
	
	@GetMapping("new")
	public String displayCreateAuthorForm(Model model) {
		model.addAttribute("authorDTO", new AuthorCreateRequestDTO(null, null));
		return "author/author-new";
	}
	
	@PostMapping("new")
	public String createNewAuthor(@ModelAttribute("authorDTO") AuthorCreateRequestDTO dto,
			RedirectAttributes redirectAttr) {
		log.info("Author name:"+dto.name());
		log.info("Author description:"+dto.description());
		redirectAttr.addFlashAttribute("authorDTO", dto);
		return "redirect:/author/create-result";

	}
	
	@GetMapping("create-result")
	public String displayCreateResult(Model model) {
		return "author/author-create-result";
	}
	

}
