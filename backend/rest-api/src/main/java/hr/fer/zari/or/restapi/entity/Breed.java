package hr.fer.zari.or.restapi.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;


//Table - breed
@TypeDefs({
    @TypeDef(
        name = "string-array",
        typeClass = StringArrayType.class
    ),
    @TypeDef(
        name = "int-array",
        typeClass = IntArrayType.class
    )
})
@Entity
@Table(name="breed")
public class Breed implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -953410575756199136L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="breedname")
	private String breedname;
	@Column(name="lifeexpectancy")
	private int lifeExpectancy;
	private int weight;
	private int height;
	@Type(type = "string-array")
    @Column(
        name = "temperament",
        columnDefinition = "text[]"
    )
	private String[] temperament;
	@Type(type = "string-array")
    @Column(
        name = "colours",
        columnDefinition = "text[]"
    )
	private String[] colours;
	private String coat;
	private String wiki;
	private String description;
	private String gender;
	
	@ManyToOne
	@JoinColumn(name="countryoforigin")
	private Country countryOfOrigin;
	
	@ManyToOne
	@JoinColumn(name="classification")
	private Classification classification;
	
	@ManyToOne
	@JoinColumn(name="species")
	private Species species;
	
	@ManyToOne
	@JoinColumn(name = "descendantof_breed")
	private Breed descendantOfBreed;
	
	protected Breed() {
		
	}
	
	public Breed(String breedname, int lifeExpectancy, int weight, int height, String[] temperament, String[] colours,
			String coat, String wiki, String description, String gender, Country countryOfOrigin,
			Classification classification, Species species, Breed descendantOfBreed) {
		super();
		this.breedname = breedname;
		this.lifeExpectancy = lifeExpectancy;
		this.weight = weight;
		this.height = height;
		this.temperament = temperament;
		this.colours = colours;
		this.coat = coat;
		this.wiki = wiki;
		this.description = description;
		this.gender = gender;
		this.countryOfOrigin = countryOfOrigin;
		this.classification = classification;
		this.species = species;
		this.descendantOfBreed = descendantOfBreed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public String getBreedname() {
		return breedname;
	}

	public int getLifeExpectancy() {
		return lifeExpectancy;
	}

	public int getWeight() {
		return weight;
	}

	public int getHeight() {
		return height;
	}

	public String[] getTemperament() {
		return temperament;
	}

	public String[] getColours() {
		return colours;
	}

	public String getCoat() {
		return coat;
	}

	public String getWiki() {
		return wiki;
	}

	public String getDescription() {
		return description;
	}

	public String getGender() {
		return gender;
	}

	public Country getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public Classification getClassification() {
		return classification;
	}

	public Species getSpecies() {
		return species;
	}

	public Breed getDescendantOfBreed() {
		return descendantOfBreed;
	}

	@Override
	public String toString() {
		return "Breed [id=" + id + ", breedname=" + breedname + ", lifeExpectancy=" + lifeExpectancy + ", weight="
				+ weight + ", height=" + height + ", temperament=" + Arrays.toString(temperament) + ", colours="
				+ Arrays.toString(colours) + ", coat=" + coat + ", wiki=" + wiki + ", description=" + description
				+ ", gender=" + gender + ", countryOfOrigin=" + countryOfOrigin + ", classification=" + classification
				+ ", species=" + species + ", descendantOfBreed=" + descendantOfBreed + "]";
	}
	
}
