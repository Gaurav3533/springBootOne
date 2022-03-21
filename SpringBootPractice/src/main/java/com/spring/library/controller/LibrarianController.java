package com.spring.library.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.spring.library.custommsg.CustomMessage;
import com.spring.library.entities.Librarian;
import com.spring.library.services.LibrarianService;

@Controller
public class LibrarianController {

	private LibrarianService service;

	public LibrarianController(LibrarianService service) {
		super();
		this.service = service;
	}


	@GetMapping("/register")
	public String openRegisterForm(Model model) {
		model.addAttribute("librarian", new Librarian());
		return "register";
	}

	//handler for registration

	@PostMapping("/loginForm")
	public String saveBook(@Valid @ModelAttribute("librarian") Librarian librarian, BindingResult result, Model model, HttpSession session) {
		try {
			if(result.hasErrors()) {
				System.out.println("ERROR: "+result.toString());
				model.addAttribute("librarian", librarian);
				return "register";
			}
			service.saveLibrarian(librarian);

			model.addAttribute("customMessage", new Librarian());
			session.setAttribute("customMessage", new CustomMessage("Successfully registered!! You can login now.","alert-success"));
			return "register";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("librarian", librarian);
			session.setAttribute("customMessage", new CustomMessage("Could not registered!!"+e.getMessage(),"alert-danger"));
			return "register";
		}

	}

	@GetMapping("/login")
	public String loginPage(Model model) {

		model.addAttribute("librarian", new Librarian());
		return "login";
	}


	@PostMapping("/processLogin")
	public String authenticateUser(@Valid @ModelAttribute ("librarian") Librarian librarian,  BindingResult result, HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		String toReturn = "";
	
			if(result.hasErrors()) {
				System.out.println("ERROR: "+result.toString());
				model.addAttribute("librarian", librarian);
				return "login";
			}
		
			System.out.println(librarian.getId());
			String userId = librarian.getId();
			String userPassword = librarian.getPassword();
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("logged", userId);

			List<Librarian> flag = service.getLibrarianByIdAndPassword(userId,userPassword);

			if (flag.size() == 0) {
				session.setAttribute("customMessage", new CustomMessage("Invalid User Credentials!!","alert-danger"));
				toReturn = "login";

			}else {
				toReturn = "redirect:books";
			}
			return toReturn;
	}
}
