package br.ufes.dcel.movieTheather.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufes.dcel.movieTheather.model.Category;
import br.ufes.dcel.movieTheather.model.Movie;
import br.ufes.dcel.movieTheather.model.TypeTech;
import br.ufes.dcel.movieTheather.repository.categories;

@Controller
@RequestMapping("/movieTheather")
public class CategoryController 
{
	 private categories myCategories;
	 
	 @Autowired
	 public CategoryController( categories myCategories )
	 {
		 this.myCategories = myCategories;
	 }
	 
	 @RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	 @PreAuthorize("hasAuthority('ADMIN')")
     public ModelAndView saveCategory( @Valid Category category, BindingResult result, RedirectAttributes attribute ) 
	 {
           if ( result.hasErrors() )
           {
        	   return addCategory( category );
           }
           
           this.myCategories.save(category);
           attribute.addFlashAttribute("mensagem", "Success! :D");
           return new ModelAndView("redirect:/movieTheather/newCategory");
     }
	 
	 
	 
	 @RequestMapping(value="/showCategories")
     public ModelAndView showMoviess() 
     {
	   	  ModelAndView mv = new ModelAndView("/movieTheather/listCategories");
	   	  List<Category> lstCategory = myCategories.findAll();
         if ( lstCategory != null) {
               mv.addObject("lstCategory", lstCategory );
         }
         
         return mv;
     }
     
	 
	 @RequestMapping(value = "/newCategory")
	 @PreAuthorize("hasAuthority('ADMIN')")
     public ModelAndView addCategory( Category category ) 
	 {
           return new ModelAndView("/movieTheather/addCategory");
     }
	
		@RequestMapping("/editCategory/{idCategory}")
		@PreAuthorize("hasAuthority('ADMIN')")
	  	public ModelAndView editCategory( @PathVariable("idCategory") Category category )
	  	{
	  		System.out.println("Id ao prencher" + category.getIdCategory());
			ModelAndView mv = new ModelAndView("/movieTheather/editCategory");
	  		mv.addObject("category", category);
	  		System.out.println("Id ao add no model" + category.getIdCategory());
	  		return mv;
	  	}
		 
		 
		@RequestMapping(value="/updateCategory/{idCategory}", method=RequestMethod.POST)
		@PreAuthorize("hasAuthority('ADMIN')")
		public ModelAndView updateCategory(@PathVariable("idCategory") Long id, Category category, 
				BindingResult result, RedirectAttributes attribute )
		{
			System.out.println("ATUALIZAR");
			System.out.println("id ao salvar"+ category.getIdCategory() );
			category.setId(id);
			
			if( result.hasErrors() )
			{
				return editCategory( category );
			}
			
			myCategories.save( category );
			
			attribute.addFlashAttribute("message", "Success! :D");
			return new ModelAndView("redirect:/movieTheather/showCategories");
		}
		
		 @RequestMapping("/removeConfirmCategory/{idMovie}")
		 @PreAuthorize("hasAuthority('ADMIN')")
	 	 public ModelAndView removeConfirm(@PathVariable("idMovie") Category category )
	 	 {
	    	 ModelAndView mv = new ModelAndView("/movieTheather/viewCategory");
	    	  mv.addObject("category", category);
	 		 return mv;
	 	 }
		
		
		 @RequestMapping("/removeCategory/{id}")
		 @PreAuthorize("hasAuthority('ADMIN')")
	 	 public ModelAndView remove( @PathVariable("id") Category category)
	 	 {
	    	 myCategories.delete(category);
	 		 return new ModelAndView("redirect:/movieTheather/showCategories");
	 	 }
		 
		 @RequestMapping("/showMoviesbyCategory/{id}")
	  	public ModelAndView viewMovie( @PathVariable("id") Category category )
	  	{
	  		ModelAndView mv = new ModelAndView("/movieTheather/listMovies");
	  		mv.addObject("movies", category.getLstMovies() );
	  		return mv;
	  	}
}
