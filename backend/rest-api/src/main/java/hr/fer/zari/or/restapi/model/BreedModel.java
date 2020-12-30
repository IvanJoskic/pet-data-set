package hr.fer.zari.or.restapi.model;


import java.util.Arrays;

import hr.fer.zari.or.restapi.entity.Breed;
import hr.fer.zari.or.restapi.entity.Classification;
import hr.fer.zari.or.restapi.entity.Country;
import hr.fer.zari.or.restapi.entity.Species;

public class BreedModel {
	private String id;
	private String breedname;
	private String lifeExpectancy;
	private String weight;
	private String height;
	private String temperament;
	private String colours;
	private String coat;
	private String wiki;
	private String description;
	private String gender;
	private String countryOfOrigin;
	private String classification;
	private String species;
	private String descendantOfBreed;
	
	public BreedModel(String id, String breedname, String lifeExpectancy, String weight, String height,
			String temperament, String colours, String coat, String wiki, String description, String gender,
			String countryOfOrigin, String classification, String species, String descendantOfBreed) {
		super();
		this.id = id;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBreedname() {
		return breedname;
	}
	public void setBreedname(String breedname) {
		this.breedname = breedname;
	}
	public String getLifeExpectancy() {
		return lifeExpectancy;
	}
	public void setLifeExpectancy(String lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getTemperament() {
		return temperament;
	}
	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}
	public String getColours() {
		return colours;
	}
	public void setColours(String colours) {
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
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getDescendantOfBreed() {
		return descendantOfBreed;
	}
	public void setDescendantOfBreed(String descendantOfBreed) {
		this.descendantOfBreed = descendantOfBreed;
	}
	@Override
	public String toString() {
		return "BreedModel [id=" + id + ", breedname=" + breedname + ", lifeExpectancy=" + lifeExpectancy + ", weight="
				+ weight + ", height=" + height + ", temperament=" + temperament + ", colours=" + colours + ", coat="
				+ coat + ", wiki=" + wiki + ", description=" + description + ", gender=" + gender + ", countryOfOrigin="
				+ countryOfOrigin + ", classification=" + classification + ", species=" + species
				+ ", descendantOfBreed=" + descendantOfBreed + "]";
	}
	
}
