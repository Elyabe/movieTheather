package br.ufes.dcel.movieTheather.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="tbl_movie")
public class Movie 
{
 
      @Id
      @Column
      @GeneratedValue(strategy=GenerationType.IDENTITY) 
      private Long idMovie; 
      
      @ManyToOne
      @JoinColumn(name="idCategory", referencedColumnName="idCategory")
      @NotNull( message="{category.NotNull}" )
      private Category category;
      

      @Size(min=3, max=25)
      @NotBlank( message="{title.NotBlank}" )
      @Column
      private String title;
      

      @NotBlank( message="{author.NotBlank}" )
      @Size(min=3, max=25)
      @Column
      private String author;


      @NotBlank( message="{director.NotBlank}" )
      @Size(min=3, max= 25)
      @Column
      private String director;
      

      @NotBlank( message="{originCountry.NotBlank}" )
      @Size(min=3, max= 25)
      @Column
      private String originCountry;
      

      @Column( nullable=true)
      @Size(min=0, max= 50)
      private String urlCover;
      

      @NotBlank( message="{sinopse.NotBlank}" )
      @Size(min=15, max= 500)
      @Column
      private String sinopse;
      


      @Column( nullable = true)
      private Date dateRealease;
      

      @NotNull( message="{yearRelease.NotNull}" )
      @Min(value=1950)
      @Max(value=3000)
      @Column
      private Long yearRelease;
      

      @Min(value=20)
      @NotNull( message="{durationMin.NotNull}" )
      @Column
      private Long durationMin;
      

      @NotNull( message="{typeTech.NotNull}" )
      @Enumerated(EnumType.STRING)
      @Column
      private TypeTech typeTech;
      
            
      @Override
      public int hashCode() 
      {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((author == null) ? 0 : author.hashCode());
			result = prime * result + ((category == null) ? 0 : category.hashCode());
			result = prime * result + ((dateRealease == null) ? 0 : dateRealease.hashCode());
			result = prime * result + ((director == null) ? 0 : director.hashCode());
			result = prime * result + ((durationMin == null) ? 0 : durationMin.hashCode());
			result = prime * result + ((idMovie == null) ? 0 : idMovie.hashCode());
			result = prime * result + ((originCountry == null) ? 0 : originCountry.hashCode());
			result = prime * result + ((sinopse == null) ? 0 : sinopse.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + ((typeTech == null) ? 0 : typeTech.hashCode());
			result = prime * result + ((urlCover == null) ? 0 : urlCover.hashCode());
			result = prime * result + ((yearRelease == null) ? 0 : yearRelease.hashCode());
			return result;
		}
      
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Movie other = (Movie) obj;
			if (author == null) {
				if (other.author != null)
					return false;
			} else if (!author.equals(other.author))
				return false;
			if (category == null) {
				if (other.category != null)
					return false;
			} else if (!category.equals(other.category))
				return false;
			if (dateRealease == null) {
				if (other.dateRealease != null)
					return false;
			} else if (!dateRealease.equals(other.dateRealease))
				return false;
			if (director == null) {
				if (other.director != null)
					return false;
			} else if (!director.equals(other.director))
				return false;
			if (durationMin == null) {
				if (other.durationMin != null)
					return false;
			} else if (!durationMin.equals(other.durationMin))
				return false;
			if (idMovie == null) {
				if (other.idMovie != null)
					return false;
			} else if (!idMovie.equals(other.idMovie))
				return false;
			if (originCountry == null) {
				if (other.originCountry != null)
					return false;
			} else if (!originCountry.equals(other.originCountry))
				return false;
			if (sinopse == null) {
				if (other.sinopse != null)
					return false;
			} else if (!sinopse.equals(other.sinopse))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			if (typeTech != other.typeTech)
				return false;
			if (urlCover == null) {
				if (other.urlCover != null)
					return false;
			} else if (!urlCover.equals(other.urlCover))
				return false;
			if (yearRelease == null) {
				if (other.yearRelease != null)
					return false;
			} else if (!yearRelease.equals(other.yearRelease))
				return false;
			return true;
		}
		
		public TypeTech getTypeTech() 
		{
			return typeTech;
		}
		
		public void setTypeTech(TypeTech typeTech) 
		{
			this.typeTech = typeTech;
		}
		
		public Long getyearRelease() 
		{
			return yearRelease;
		}
		
		public void setyearRelease(Long yearRelease) 
		{
			this.yearRelease = yearRelease;
		}
		
	      
	    public Long getDurationMin() 
	    {
		     return durationMin;
	    }
	      
		public void setDurationMin(Long durationMin) 
		{
			this.durationMin = durationMin;
		}
		
		   
	    public Category getCategory() 
	    {
			return category;
		}
	    
		public void setCategory(Category category) 
		{
			this.category = category;
		}

	

      
	    public Long getIdMovie()
	    {
	        return idMovie;
	    }
	    
	    public void setIdMovie(Long idMovie ) 
	    {
	        this.idMovie = idMovie;
	    }
     
     
		public String getTitle() 
		{
			return title;
		}
		
		public void setTitle(String title) 
		{
			this.title = title;
		}
		
		public String getAuthor() 
		{
			return author;
		}
		public void setAuthor(String author) 
		{
			this.author = author;
		}
		
		public String getDirector() 
		{
			return director;
		}
		
		public void setDirector(String director) 
		{
			this.director = director;
		}
		
		public String getOriginCountry() 
		{
			return originCountry;
		}
		
		public void setOriginCountry(String originCountry) 
		{
			this.originCountry = originCountry;
		}
		
		public String getUrlCover() 
		{
			return urlCover;
		}
		
		public void setUrlCover(String urlCover) 
		{
			this.urlCover = urlCover;
		}
		
		public String getSinopse() 
		{
			return sinopse;
		}
		
		public void setSinopse(String sinopse) 
		{
			this.sinopse = sinopse;
		}
		
		public Date getDateRealease() 
		{
			return dateRealease;
		}
		
		public void setDateRealease(Date dateRealease) 
		{
			this.dateRealease = dateRealease;
		}
	      
	      
	 
} 