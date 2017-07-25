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
import br.ufes.dcel.movieTheather.repository.movies;

@Controller
@RequestMapping("/movieTheather")
public class movieController 
{
       
      private movies myMovies;
      private categories myCategories;
 
     @Autowired
      public movieController( movies myMovies,  categories myCategories ) 
     {
            this.myMovies = myMovies;
            this.myCategories = myCategories;
      }
 
 
      @RequestMapping
      public ModelAndView loadHome()
      {
    	  return new ModelAndView("redirect:/movieTheather/showMovies");
      }
      
      @RequestMapping(value="/showMovies")
      public ModelAndView showMoviess() 
      {
    	  ModelAndView mv = new ModelAndView("/movieTheather/listMovies");
    	  List<Movie> listaMovies = myMovies.findAll();
          if (listaMovies != null) {
                mv.addObject("movies", listaMovies );
          }
          return mv;
      }
      
      @PreAuthorize("hasRole('admin')")
      @RequestMapping(value="/newMovie")
      public ModelAndView newMovie( Movie movie ) 
      {   
    	  ModelAndView mv = new ModelAndView("/movieTheather/addMovie");
    	  List<Category> lstCategory = myCategories.findAll();
    	
    	  mv.addObject("lstCategory", lstCategory);
    	  mv.addObject("lstTech", TypeTech.values() );
    	  return mv;
      }
      
      @PreAuthorize("hasRole('admin')")
      @RequestMapping(value = "/saveMovie", method = RequestMethod.POST)
      public ModelAndView addMovie( @Valid Movie movie,
    		  BindingResult result, RedirectAttributes attribute )
      {
    	  System.out.println("SALVAR");
    	  
    	  	if( result.hasErrors() )
			{
				return newMovie( movie );
			}
			if ( movie.getUrlCover().isEmpty() ) 
				movie.setUrlCover("cover_default.png");
				
		    myMovies.save(movie);
		    attribute.addFlashAttribute("mensagem", "Success! :D");
            return new ModelAndView("redirect:/movieTheather/newMovie");
      }
      
      	@PreAuthorize("hasRole('admin')")
	    @RequestMapping("/editMovie/{idMovie}")
	  	public ModelAndView editMovie( @PathVariable("idMovie") Movie movie )
	  	{
	  		ModelAndView mv = new ModelAndView("/movieTheather/editMovie");
	  		mv.addObject("lstCategory", myCategories.findAll() );
	  		mv.addObject("movie", movie );
	  		mv.addObject("lstTech", TypeTech.values() );
	  		return mv;
	  	}
	    
	    @RequestMapping("/viewMovie/{idMovie}")
	  	public ModelAndView viewMovie( @PathVariable("idMovie") Movie movie )
	  	{
	  		ModelAndView mv = new ModelAndView("/movieTheather/viewMovie");
	  		mv.addObject("movie", movie );
	  		return mv;
	  	}
	    
	      
	    @PreAuthorize("hasRole('admin')")
	    @RequestMapping(value="/updateMovie", method=RequestMethod.POST)
		public ModelAndView updateMovie(@Valid Movie movie, BindingResult result, RedirectAttributes attribute )
		{
			System.out.println("ATUALIZAR");
			
			if( result.hasErrors() )
			{
				return editMovie( movie );
			}
			
			myMovies.save( movie );
			attribute.addFlashAttribute("message", "Success! :D");
			return new ModelAndView("redirect:/movieTheather/showMovies");
		}
	    
	     @RequestMapping("/removeMovie/{id}")
	     @PreAuthorize("hasRole('admin')")
	 	 public String remove( @PathVariable("id") Movie movie )
	 	 {
	    	  this.myMovies.delete(movie);;
	 		 return "redirect:/movieTheather/showMovies";
	 	 }
	    
}
