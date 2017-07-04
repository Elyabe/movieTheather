package br.ufes.dcel.movieTheather.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
	 
	 
	 
	/* @RequestMapping("/{category}")
	 public ModelAndView listar( Category category )
	 {
		 
		 List<Movie> lstMovies = myCategories.findOne( category.getIdCategory() ).getLstMovies();
		 ModelAndView mv = new ModelAndView("/movieTheather/listMovies");
		 mv.addObject("movies", lstMovies);
		 
		 return mv;
	 }*/
	 
	 @RequestMapping(value="/showCategories")
     public ModelAndView showMoviess() 
     {
   	  ModelAndView mv = new ModelAndView("/movieTheather/listCategories");
   	  List<Category> lstCategory = myCategories.findAll();
//   	  List<Movie> listaMovies = myCategories.findOne(2L).getLstMovies();
         if ( lstCategory != null) {
               mv.addObject("lstCategory", lstCategory );
         }
         
         return mv;
     }
     
	 
	 @RequestMapping(value = "/newCategory")
     public ModelAndView addCategory( Category category ) 
	 {
           return new ModelAndView("/movieTheather/addCategory");
     }
	
	@RequestMapping("/editCategory/{idCategory}")
  	public ModelAndView editCategory( @PathVariable("idCategory") Category category )
  	{
  		System.out.println("Id ao prencher" + category.getIdCategory());
		ModelAndView mv = new ModelAndView("/movieTheather/editCategory");
  		mv.addObject("category", category);
  		System.out.println("Id ao add no model" + category.getIdCategory());
  		return mv;
  	}
	 
	 
	@RequestMapping(value="/updateCategory/{idCategory}", method=RequestMethod.POST)
	public ModelAndView updateCategory(@PathVariable("idCategory") Long id, Category category, BindingResult result, RedirectAttributes attribute )
	{
		System.out.println("ATUALIZAR");
		System.out.println("id ao salvar"+ category.getIdCategory() );
		category.setId(id);
		if( result.hasErrors() )
		{
			return editCategory( category );
		}
		
		System.out.println("id ao salvar"+ category.getIdCategory());
		myCategories.save( category );
		
		attribute.addFlashAttribute("mensagem", "Success! :D");
		return new ModelAndView("redirect:/movieTheather/");
	}
	
	 @RequestMapping("/removeConfirmCategory/{idMovie}")
 	 public ModelAndView removeConfirm(@PathVariable("idMovie") Category category )
 	 {
    	 ModelAndView mv = new ModelAndView("/movieTheather/viewCategory");
    	  mv.addObject("category", category);
 		 return mv;
 	 }
	
	
	 @RequestMapping("/removeCategory/{id}")
 	 public String remove( @PathVariable("id") Category category )
 	 {
    	  myCategories.delete(category);
 		 return "redirect:/movieTheather/showCategories";
 	 }
}
