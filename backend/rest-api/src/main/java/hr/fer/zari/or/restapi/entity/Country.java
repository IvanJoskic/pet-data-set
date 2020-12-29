package hr.fer.zari.or.restapi.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6863802966670084160L;
	@Id
	@Column(name="countryname")
	private String countryName;
	@Column(name="countryabbrev")
	private String countryAbbrev;
	
	@OneToMany(mappedBy = "countryOfOrigin")
	private List<Breed> breeds;
	
	protected Country() {
		
	}
	
	public Country(String countryName, String countryAbbrev) {
		super();
		this.countryName = countryName;
		this.countryAbbrev = countryAbbrev;
	}
	
	public String getCountryName() {
		return countryName;
	}
	public String getCountryAbbrev() {
		return countryAbbrev;
	}
	@Override
	public String toString() {
		return "Country [countryName=" + countryName + ", countryAbbrev=" + countryAbbrev + "]";
	}
}
