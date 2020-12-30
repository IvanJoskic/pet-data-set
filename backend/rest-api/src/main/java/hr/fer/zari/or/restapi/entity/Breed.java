package hr.fer.zari.or.restapi.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
	private Integer lifeExpectancy;
	private Integer weight;
	private Integer height;
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
	
	public Map<String, Integer> provideFieldStatus() {
		Map<String, Integer> result = new HashMap<String, Integer>();
		
		result.put("breedname", this.breedname != null ? 1 : 0);
		result.put("lifeExpectancy", this.lifeExpectancy != null ? 1 : 0);
		result.put("weight", this.weight != null ? 1 : 0);
		result.put("height", this.height != null ? 1 : 0);
		result.put("temperament", this.temperament != null ? 1 : 0);
		result.put("colours", this.colours != null ? 1 : 0);
		result.put("coat", this.coat != null ? 1 : 0);
		result.put("wiki", this.wiki != null ? 1 : 0);
		result.put("description", this.description != null ? 1 : 0);
		result.put("gender", this.gender != null ? 1 : 0);
		result.put("countryOfOrigin", this.countryOfOrigin != null ? 1 : 0);
		result.put("classification", this.classification != null ? 1 : 0);
		result.put("species", this.species != null ? 1 : 0);
		result.put("descendantOfBreed", this.descendantOfBreed != null ? 1 : 0);
		
		return result;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}

	public void setLifeExpectancy(int lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setTemperament(String[] temperament) {
		this.temperament = temperament;
	}

	public void setColours(String[] colours) {
		this.colours = colours;
	}

	public void setCoat(String coat) {
		this.coat = coat;
	}

	public void setWiki(String wiki) {
		this.wiki = wiki;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setCountryOfOrigin(Country countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public void setDescendantOfBreed(Breed descendantOfBreed) {
		this.descendantOfBreed = descendantOfBreed;
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
