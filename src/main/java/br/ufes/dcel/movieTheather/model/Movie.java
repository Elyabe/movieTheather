package br.ufes.dcel.movieTheather.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_movie")
public class Movie {
 
      @Id
      @Column
      @GeneratedValue(strategy=GenerationType.IDENTITY) 
      private Long idMovie; 
      
      @ManyToOne
      @JoinColumn(name="idCategory", referencedColumnName="idCategory")
      private Category category;
      
      @Column( nullable=true)
      private String title;
      
      @Column( nullable=true)
      private String author;

      @Column( nullable=true)
      private String director;
      
      @Column( nullable=true)
      private String originCountry;
      
      @Column(nullable=true)
      private String urlCover;
      
      @Column( nullable=true)
      private String sinopse;
      
      @Column(nullable=true)
      private Date dateRealease;
      
      @Column( nullable=true)
      private Long durationMin;
      
            
      @Column( nullable=true)
      private String description;
      
      public Long getDurationMin() {
		return durationMin;
	}
	public void setDurationMin(Long durationMin) {
		this.durationMin = durationMin;
	}
	
	   
    public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	

      
      public Long getIdMovie() {
            return idMovie;
      }
      public void setIdMovie(Long idMovie ) {
            this.idMovie = idMovie;
      }
     
      public String getDescription() {
            return description;
      }
      public void setDescription(String description) {
            this.description = description;
      }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getUrlCover() {
		return urlCover;
	}
	public void setUrlCover(String urlCover) {
		this.urlCover = urlCover;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public Date getDateRealease() {
		return dateRealease;
	}
	public void setDateRealease(Date dateRealease) {
		this.dateRealease = dateRealease;
	}
      
      
 
} 