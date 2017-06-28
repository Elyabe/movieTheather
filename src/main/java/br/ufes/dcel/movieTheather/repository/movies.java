package br.ufes.dcel.movieTheather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.dcel.movieTheather.model.Movie;

public interface movies extends JpaRepository<Movie, Long> {
	   
   
    List<Movie> findByAuthor(String author);
   
    List<Movie> findByTitle(String title);

}