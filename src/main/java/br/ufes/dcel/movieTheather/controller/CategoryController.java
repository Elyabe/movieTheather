package br.ufes.dcel.movieTheather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.dcel.movieTheather.model.Category;
import br.ufes.dcel.movieTheather.model.Movie;
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
     public String saveCategory( Category category) 
	 {
          	this.myCategories.save(category);
           return "redirect:/movieTheather/newMovie";
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
	 
	 @RequestMapping("/removeCategory/{id}")
 	 public String remove( @PathVariable("id") Category category )
 	 {
    	  this.myCategories.delete(category);
 		 return "redirect:/movieTheather/showMovies";
 	 }
}
