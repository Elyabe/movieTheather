package br.ufes.dcel.movieTheather.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {
 
      @Id
      @GeneratedValue(strategy=GenerationType.AUTO) 
      private Long id; 
      
      private String title;
      private String author;
      private String director;
      private String originCountry;
      private String urlCover;
      private String sinopse;
      private Date dateRealease;
      
      
      @Column(name="titulo", nullable=false)
       private String titulo; 
 
      @Column(name="isbn", nullable=false, unique=true)
      private String isbn; 
      
      @Column(name="autor", nullable=false)
      private String autor;
      
      @Column(name="description", nullable=true)
      private String description;
      
      public Long getId() {
            return id;
      }
      public void setId(Long id) {
            this.id = id;
      }
      public String getTitulo() {
            return titulo;
      }
      public void setTitulo(String titulo) {
            this.titulo = titulo;
      }
      public String getIsbn() {
            return isbn;
      }
      public void setIsbn(String isbn) {
            this.isbn = isbn;
      }
      public String getAutor() {
            return autor;
      }
      public void setAutor(String autor) {
            this.autor = autor;
      }
      public String getDescription() {
            return description;
      }
      public void setDescription(String description) {
            this.description = description;
      }
 
} 