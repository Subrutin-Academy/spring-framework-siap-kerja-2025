package com.subrutin.webcatalog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.subrutin.webcatalog.dto.AuthorCreateRequestDTO;
import com.subrutin.webcatalog.dto.AuthorResponseDTO;
import com.subrutin.webcatalog.service.AuthorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("author")
public class AuthorController {
	
	private final AuthorService authorService;
	
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
		authorService.createNewAuthor(dto);
		redirectAttr.addFlashAttribute("authorDTO", dto);
		return "redirect:/author/create-result";

	}
	
	@GetMapping("list")
	public String displayAuthorList(Model model) {
		List<AuthorResponseDTO> authorList = authorService.findAuthorList();
		model.addAttribute("authors", authorList);
		return "author/author-list";
	}
	
	@GetMapping("create-result")
	public String displayCreateResult(Model model) {
		return "author/author-create-result";
	}
	

}
