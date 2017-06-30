package br.ufes.dcel.movieTheather.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufes.dcel.movieTheather.model.Category;

public interface categories extends JpaRepository<Category, Long>
{

}
