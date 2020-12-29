package hr.fer.zari.or.restapi.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="species")
public class Species implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8811112005250230275L;
	@Id
	@Column(name="colloquialname")
	private String colloquialName;
	@Column(name="speciesname")
	private String speciesName;
	
	@OneToMany(mappedBy = "species")
	private List<Breed> breeds;
	
	protected Species() {
		
	}
	
	public Species(String colloquialName, String speciesName) {
		super();
		this.colloquialName = colloquialName;
		this.speciesName = speciesName;
	}
	
	public String getColloquialName() {
		return colloquialName;
	}
	public String getSpeciesName() {
		return speciesName;
	}
	@Override
	public String toString() {
		return "Species [colloquialName=" + colloquialName + ", speciesName=" + speciesName + "]";
	}
	
}
