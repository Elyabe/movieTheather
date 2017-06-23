package br.ufes.dcel.movieTheather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufes.dcel.movieTheather.model.Movie;
import br.ufes.dcel.movieTheather.repository.movies;

@Controller
@RequestMapping("/")
public class movieController {
      
      private movies myMovies;
 
      @Autowired
      public movieController( movies myMovies) {
            this.myMovies = myMovies;
      }
 
      @RequestMapping(value = "/{autor}", method = RequestMethod.GET)
      public String listaLivros(@PathVariable("autor") String autor, Model model) {
            List<Movie> listaLivros = myMovies.findByAutor(autor);
            if (listaLivros != null) {
                  model.addAttribute("livros", listaLivros);
            }
            return "listaLivros";
      }
 
      @RequestMapping(value = "/{autor}", method = RequestMethod.POST)
      public String adicionaLivroAutor(@PathVariable("autor") String autor, Movie livro) {
            livro.setAutor(autor);
            myMovies.save(livro);
            return "redirect:/{autor}";
      }
}
