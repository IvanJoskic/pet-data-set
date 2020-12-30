package hr.fer.zari.or.restapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hr.fer.zari.or.restapi.entity.Breed;
import hr.fer.zari.or.restapi.exception.BreedNotFoundException;
import hr.fer.zari.or.restapi.model.BreedModel;
import hr.fer.zari.or.restapi.service.AnimalRepository;

@RestController
public class BreedController {
	@Autowired
	private AnimalRepository repository;
	
	@GetMapping(path = "/breeds")
	public List<Breed> retrieveAllBreeds() {
		return repository.findAll();
	}
	
	@GetMapping(path = "/breeds/{id}")
	public Breed getBreedById(@PathVariable long id) {
		Optional<Breed> breed = repository.findById(id);
		// TODO throw error for breed == null
		// also create the exception to throw
		return breed.isPresent() ? breed.get() : null;
	}
	
	@GetMapping(path = "/breeds/{id}/colours")
	public String[] getColoursForBreed(@PathVariable long id) {
		Optional<Breed> breed = repository.findById(id);
		
		if (!breed.isPresent()) {
			// TODO throw exception
		}
		
		return breed.get().getColours();
	}
	
	@GetMapping(path = "/breeds/{id}/descendantOf")
	public Breed getDescendantOfForBreed(@PathVariable long id) {
		Optional<Breed> breed = repository.findById(id);
		
		if (!breed.isPresent()) {
			throw new BreedNotFoundException(id);
		}
		
		return breed.get().getDescendantOfBreed();
	}
	
	@GetMapping(path = "/breeds/description")
	public BreedModel getFieldDescriptions() {		
		return new BreedModel("Unique identifier.", "Name of the breed.", "Number. Average life expectancy.", "Number. Average weight for the breed.", "Number. Average height for the breed."
				, "Array of strings. Describes common personality traits of the breed.", "Array of strings. Common fur colours and patterns.", "String. Describes the type of coat."
				, "String. Wiki-handle to the breeds wiki.", "String. Short description of the breed.", "String. Gender the traits apply to.", "Country the breed originates from.", "A classification of the breed based on common traits."
				, "Species name.", "The breeds ancestor.");
	}
	
	@PostMapping("/breeds")
	public ResponseEntity<Object> createBreed(@RequestBody Breed breed) {
		Breed savedBreed = repository.save(breed);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedBreed.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/breeds/{id}")
	public void deleteBreed(@PathVariable long id) {
		repository.deleteById(id);
	}

	@PutMapping(path = "/breeds/{id}")
	public ResponseEntity<Object> updateBreed(@PathVariable long id, @RequestBody Breed breed) {
		breed.setId(id);
		Map<String, Integer> fieldStatus = breed.provideFieldStatus();
		Breed updatedBreed = repository.getOne(id);
		
		  fieldStatus.forEach((k, v) -> { if(v == 1) { if (k.equals("breedname")) {
		  updatedBreed.setBreedname(breed.getBreedname()); } else if
		  (k.equals("lifeExpectancy")) {
		  updatedBreed.setLifeExpectancy(breed.getLifeExpectancy()); } else if
		  (k.equals("weight")) { updatedBreed.setWeight(breed.getWeight()); } else if
		  (k.equals("height")) { updatedBreed.setHeight(breed.getHeight()); } else if
		  (k.equals("temperament")) {
		  updatedBreed.setTemperament(breed.getTemperament()); } else if
		  (k.equals("colours")) { updatedBreed.setColours(breed.getColours()); } else
		  if (k.equals("coat")) { updatedBreed.setCoat(breed.getCoat()); } else if
		  (k.equals("wiki")) { updatedBreed.setWiki(breed.getWiki()); } else if
		  (k.equals("description")) {
		  updatedBreed.setDescription(breed.getDescription()); } else if
		  (k.equals("gender")) { updatedBreed.setGender(breed.getGender()); } else if
		  (k.equals("countryOfOrigin")) {
		  updatedBreed.setCountryOfOrigin(breed.getCountryOfOrigin()); } else if
		  (k.equals("classification")) {
		  updatedBreed.setClassification(breed.getClassification()); } else if
		  (k.equals("species")) { updatedBreed.setSpecies(breed.getSpecies()); } else
		  if (k.equals("descendantOfBreed")) {
		  updatedBreed.setDescendantOfBreed(breed.getDescendantOfBreed()); } }});
		 
		Breed newBreed = repository.save(updatedBreed);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(newBreed.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}
