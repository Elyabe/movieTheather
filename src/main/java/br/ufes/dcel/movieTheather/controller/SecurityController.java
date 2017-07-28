package br.ufes.dcel.movieTheather.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufes.dcel.movieTheather.model.User;

@Controller
public class SecurityController {

	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal User user) {
		if (user != null) {
			System.out.println("USER: " + user.getUsername() + user.getRole());
			return "redirect:/movieTheather";
		}
		return "/movieTheather/login";
	}
	
}