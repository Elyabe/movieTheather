package br.ufes.dcel.movieTheather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufes.dcel.movieTheather.model.Movie;
import br.ufes.dcel.movieTheather.repository.movies;
@RestController
public class AjaxController {

	@Autowired
	movies myMovies;
	
	@GetMapping("/pesquisaAjax")
	public List<Movie> pesquisar(Movie movie) {
		
		ExampleMatcher matcher = ExampleMatcher.matching()
										.withIgnoreCase()
										.withStringMatcher(StringMatcher.STARTING);
		
		Example<Movie> example = Example.of(movie, matcher);
		List<Movie> allMovies = myMovies.findAll(example);
		return allMovies;
	}
	
}