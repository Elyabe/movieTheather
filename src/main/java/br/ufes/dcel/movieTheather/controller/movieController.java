package br.ufes.dcel.movieTheather.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 
     /* @RequestMapping(value = "/{author}", method = RequestMethod.GET)
      public String listaLivros(@PathVariable("author") String author, Model model) {
            List<Movie> lstMovies = myMovies.findByAuthor(author);
            if (lstMovies != null) {
                  model.addAttribute("movies", lstMovies);
            }
            return "listaLivros";
      }*/
 
      @RequestMapping
      public ModelAndView loadHome()
      {
    	  return new ModelAndView("movieTheather/home");
      }
      
      @RequestMapping(value="/showMovies")
      public ModelAndView showMoviess( RedirectAttributes attribute ) 
      {
    	  ModelAndView mv = new ModelAndView("/movieTheather/listMovies");
    	  List<Movie> listaMovies = myMovies.findAll();
//    	  List<Movie> listaMovies = myCategories.findOne(2L).getLstMovies();
          if (listaMovies != null) {
                mv.addObject("movies", listaMovies );
          }
          return mv;
      }
      
      @RequestMapping(value="/newMovie")
      public ModelAndView newMovie( Movie movie ) 
      {   
    	  ModelAndView mv = new ModelAndView("/movieTheather/addMovie");
    	  List<Category> lstCategory = myCategories.findAll();
    	  ;
    	 /*Category cat1 = new Category();
    	  Category cat2 = new Category();
    	  
    	  cat1.setLabel("cat1");
    	  cat2.setLabel("cat2");
    	  
    	  lstCategory.add(cat1);
    	  lstCategory.add(cat2);*/
    	  
    	  mv.addObject("lstCategory", lstCategory);
    	  mv.addObject("lstTech", TypeTech.values() );
    	  return mv;
      }
      
      @RequestMapping(value = "/saveMovie", method = RequestMethod.POST)
      public ModelAndView adicionaFilme( @Valid Movie movie,
    		  BindingResult result, RedirectAttributes attribute )
      {
    	  System.out.println("SALVAR");
		
			if( result.hasErrors() )
			{
				return newMovie( movie );
			}
		    myMovies.save(movie);
		    attribute.addFlashAttribute("mensagem", "Success! :D");
            return new ModelAndView("redirect:/movieTheather/newMovie");
      }
      
    @RequestMapping("/editMovie/{idMovie}")
  	public ModelAndView editMovie( @PathVariable("idMovie") Movie movie )
  	{
  		ModelAndView mv = new ModelAndView("/movieTheather/editMovie");
  		mv.addObject("lstCategory", myCategories.findAll() );
  		mv.addObject("movie", movie );
  		mv.addObject("lstTech", TypeTech.values() );
  		return mv;
  	}
      
    @RequestMapping(value="/updateMovie", method=RequestMethod.POST)
	public ModelAndView atualizar(@Valid Movie movie, BindingResult result, RedirectAttributes attribute )
	{
		System.out.println("ATUALIZAR");
		
		if( result.hasErrors() )
		{
			return editMovie( movie );
		}
		
		myMovies.save( movie );
		attribute.addFlashAttribute("mensagem", "Success! :D");
		return new ModelAndView("redirect:/movieTheather/");
	}
    
     @RequestMapping("/removeMovie/{id}")
 	 public String remove( @PathVariable("id") Movie movie )
 	 {
    	  this.myMovies.delete(movie);;
 		 return "redirect:/movieTheather/showMovies";
 	 }
}
