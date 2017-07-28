package br.ufes.dcel.movieTheather.repository;
 
 import org.springframework.data.jpa.repository.JpaRepository;
 
 import br.ufes.dcel.movieTheather.model.User;
 
 public interface users extends JpaRepository<User, String>{
	  User findByUsername(String username);
 
 }