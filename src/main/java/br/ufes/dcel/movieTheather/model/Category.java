package br.ufes.dcel.movieTheather.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_category")
public class Category 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idCategory;
	
	@Column
	private String label;
	
	@Column
	private Long ageLimit;
	
	@Column
	private String observation;
	
	
	@OneToMany(mappedBy = "category", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Movie> lstMovies;

	public Long getIdCategory() {
		return idCategory;
	}

	public void setId(Long idCategory ) {
		this.idCategory = idCategory;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(Long ageLimit) {
		this.ageLimit = ageLimit;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public List<Movie> getLstMovies() {
		return lstMovies;
	}

	public void setLstMovies(List<Movie> lstMovies) {
		this.lstMovies = lstMovies;
	}
	
}
