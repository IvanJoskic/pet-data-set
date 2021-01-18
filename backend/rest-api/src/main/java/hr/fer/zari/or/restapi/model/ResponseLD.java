package hr.fer.zari.or.restapi.model;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import hr.fer.zari.or.restapi.entity.Breed;
import hr.fer.zari.or.restapi.entity.Classification;
import hr.fer.zari.or.restapi.entity.Country;
import hr.fer.zari.or.restapi.entity.Species;

public class ResponseLD<T> {
	
	private Map<String, String> jsonLd;
	private long id;
	private String breedname;
	private Integer lifeExpectancy;
	private Integer weight;
	private Integer height;
	private String[] temperament;
	private String[] colours;
	private String coat;
	private String wiki;
	private String description;
	private String gender;
	private Country countryOfOrigin;
	private Classification classification;
	private Species species;
	private Breed descendantOfBreed;
	private URI image;
	
	public ResponseLD(T breed, String image) {
		super();
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("@context", "https://schema.org/");
		this.jsonLd = tempMap;
		Breed b = (Breed) breed;
		this.breedname = b.getBreedname();
		this.lifeExpectancy = b.getLifeExpectancy();
		this.weight = b.getWeight();
		this.height = b.getHeight();
		this.temperament = b.getTemperament();
		this.colours = b.getColours();
		this.coat = b.getCoat();
		this.wiki = b.getWiki();
		this.description = b.getDescription();
		this.gender = b.getGender();
		this.countryOfOrigin = b.getCountryOfOrigin();
		this.classification = b.getClassification();
		this.species = b.getSpecies();
		this.descendantOfBreed = b.getDescendantOfBreed();
		try {
			this.image = new URI(image);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResponseLD(T breed, URI image) {
		super();
		Map<String, String> tempMap = new HashMap<>();
		tempMap.put("@context", "https://schema.org/");
		this.jsonLd = tempMap;
		Breed b = (Breed) breed;
		this.breedname = b.getBreedname();
		this.lifeExpectancy = b.getLifeExpectancy();
		this.weight = b.getWeight();
		this.height = b.getHeight();
		this.temperament = b.getTemperament();
		this.colours = b.getColours();
		this.coat = b.getCoat();
		this.wiki = b.getWiki();
		this.description = b.getDescription();
		this.gender = b.getGender();
		this.countryOfOrigin = b.getCountryOfOrigin();
		this.classification = b.getClassification();
		this.species = b.getSpecies();
		this.descendantOfBreed = b.getDescendantOfBreed();
		this.image = image;

	}
	
	

	public Map<String, String> getJsonLd() {
		return jsonLd;
	}
	public void setJsonLd(Map<String, String> jsonLd) {
		this.jsonLd = jsonLd;
	}
	public URI getImage() {
		return image;
	}
	public void setImage(String image) {
		try {
			this.image = new URI(image);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "Response [image=" + image + ", id=" + id + ", breedname=" + breedname + ", lifeExpectancy="
				+ lifeExpectancy + ", weight=" + weight + ", height=" + height + ", temperament="
				+ Arrays.toString(temperament) + ", colours=" + Arrays.toString(colours) + ", coat=" + coat + ", wiki="
				+ wiki + ", description=" + description + ", gender=" + gender + ", countryOfOrigin=" + countryOfOrigin
				+ ", classification=" + classification + ", species=" + species + ", descendantOfBreed="
				+ descendantOfBreed + "]";
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBreedname() {
		return breedname;
	}
	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}
	public Integer getLifeExpectancy() {
		return lifeExpectancy;
	}
	public void setLifeExpectancy(Integer lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String[] getTemperament() {
		return temperament;
	}
	public void setTemperament(String[] temperament) {
		this.temperament = temperament;
	}
	public String[] getColours() {
		return colours;
	}
	public void setColours(String[] colours) {
		this.colours = colours;
	}
	public String getCoat() {
		return coat;
	}
	public void setCoat(String coat) {
		this.coat = coat;
	}
	public String getWiki() {
		return wiki;
	}
	public void setWiki(String wiki) {
		this.wiki = wiki;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Country getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(Country countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public Classification getClassification() {
		return classification;
	}
	public void setClassification(Classification classification) {
		this.classification = classification;
	}
	public Species getSpecies() {
		return species;
	}
	public void setSpecies(Species species) {
		this.species = species;
	}
	public Breed getDescendantOfBreed() {
		return descendantOfBreed;
	}
	public void setDescendantOfBreed(Breed descendantOfBreed) {
		this.descendantOfBreed = descendantOfBreed;
	}
	public void setImage(URI image) {
		this.image = image;
	}
	
}
