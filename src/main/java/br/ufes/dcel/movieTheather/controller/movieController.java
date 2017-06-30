package br.ufes.dcel.movieTheather.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufes.dcel.movieTheather.model.Category;
import br.ufes.dcel.movieTheather.model.Movie;
import br.ufes.dcel.movieTheather.repository.categories;
import br.ufes.dcel.movieTheather.repository.movies;

@Controller
@RequestMapping("/movieTheather")
public class movieController {
       
      private movies myMovies;
      private categories myCategories;
 
     @Autowired
      public movieController( movies myMovies,  categories myCategories ) {
            this.myMovies = myMovies;
            this.myCategories = myCategories;
      }
 
      @RequestMapping(value = "/{author}", method = RequestMethod.GET)
      public String listaLivros(@PathVariable("author") String author, Model model) {
            List<Movie> lstMovies = myMovies.findByAuthor(author);
            if (lstMovies != null) {
                  model.addAttribute("movies", lstMovies);
            }
            return "listaLivros";
      }
 
      @RequestMapping("/home")
      public ModelAndView loadHome()
      {
    	  return new ModelAndView("movieTheather/home");
      }
      
      @RequestMapping(value="/Movies")
      public ModelAndView showMoviess() 
      {
    	  ModelAndView mv = new ModelAndView("/movieTheather/listMovies");
    	  List<Movie> listaMovies = myMovies.findAll();
//    	  List<Movie> listaMovies = myCategories.findOne(2L).getLstMovies();
          if (listaMovies != null) {
                mv.addObject("movies", listaMovies );
          }
          return mv;
      }
      
      @RequestMapping(value="/new")
      public ModelAndView newMovie( Movie movie ) 
      {   
    	  ModelAndView mv = new ModelAndView("/movieTheather/addMovie");
    	  List<Category> lstCategory = myCategories.findAll();
    	 /*Category cat1 = new Category();
    	  Category cat2 = new Category();
    	  
    	  cat1.setLabel("cat1");
    	  cat2.setLabel("cat2");
    	  
    	  lstCategory.add(cat1);
    	  lstCategory.add(cat2);*/
    	  
    	  mv.addObject("lstCategory", lstCategory);
    	  return mv;
      }
      
      @RequestMapping(value = "/{author}", method = RequestMethod.POST)
      public String adicionaFilme(@PathVariable("author") String author, @Valid Movie movie) 
      {
            movie.setAuthor(author);
           
            myMovies.save(movie);
            
            return "redirect:/movieTheather/Movies";
      }
      
      @RequestMapping("/remove")
 	 public String remove( Long id)
 	 {
    	  this.myMovies.delete(6L);
 		 return "redirect:/movieTheather/Movies";
 	 }
}
