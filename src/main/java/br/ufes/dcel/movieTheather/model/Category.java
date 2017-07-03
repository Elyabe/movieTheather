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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ageLimit == null) ? 0 : ageLimit.hashCode());
		result = prime * result + ((idCategory == null) ? 0 : idCategory.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((lstMovies == null) ? 0 : lstMovies.hashCode());
		result = prime * result + ((observation == null) ? 0 : observation.hashCode());
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
		Category other = (Category) obj;
		if (ageLimit == null) {
			if (other.ageLimit != null)
				return false;
		} else if (!ageLimit.equals(other.ageLimit))
			return false;
		if (idCategory == null) {
			if (other.idCategory != null)
				return false;
		} else if (!idCategory.equals(other.idCategory))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (lstMovies == null) {
			if (other.lstMovies != null)
				return false;
		} else if (!lstMovies.equals(other.lstMovies))
			return false;
		if (observation == null) {
			if (other.observation != null)
				return false;
		} else if (!observation.equals(other.observation))
			return false;
		return true;
	}

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
