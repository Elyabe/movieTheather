package br.ufes.dcel.movieTheather.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.dcel.movieTheather.model.Role;
import br.ufes.dcel.movieTheather.model.User;
import br.ufes.dcel.movieTheather.repository.users;

@Controller
@RequestMapping("/movieTheather")
public class UserController 
{
	 private users myUsers;
	 
	 @Autowired
     public UserController( users _user ) 
	 {
           this.myUsers = _user;
     }
	 
	 @RequestMapping(value="/newUser")
	 @PreAuthorize("hasAuthority('ADMIN')")
     public ModelAndView newUser( User user) 
     {
	   	  ModelAndView mv = new ModelAndView("/movieTheather/addUser");
         
               mv.addObject("lstRoles", Role.values());
         
         return mv;
     }
	 
	 
	 @PreAuthorize("hasAuthority('ADMIN')")
     @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
     public ModelAndView addUser( @Valid User user,
   		  BindingResult result, RedirectAttributes attribute )
     {
   	  System.out.println("SALVAR");
   	  
   	  	if( result.hasErrors() )
			{
				return newUser( user );
			}
			
   	  	
		   	String pwd = user.getPassword();
		 	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		 	String hashPwd = bc.encode(pwd);
		 	
		 	user.setPassword(hashPwd);
		    myUsers.save(user);
		    attribute.addFlashAttribute("mensagem", "Success! :D");
           return new ModelAndView("redirect:/movieTheather/newUser");
     }
}
