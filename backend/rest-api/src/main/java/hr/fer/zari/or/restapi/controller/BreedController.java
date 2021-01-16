package hr.fer.zari.or.restapi.controller;

import java.awt.Image;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletMapping;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.Affordance;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import hr.fer.zari.or.restapi.entity.Breed;
import hr.fer.zari.or.restapi.exception.BreedNotFoundException;
import hr.fer.zari.or.restapi.exception.InvalidInputException;
import hr.fer.zari.or.restapi.exception.NoDescendantFoundException;
import hr.fer.zari.or.restapi.model.BreedModel;
import hr.fer.zari.or.restapi.model.ResponseModel;
import hr.fer.zari.or.restapi.service.AnimalRepository;
import hr.fer.zari.or.restapi.service.WikiApiService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class BreedController {
	@Autowired
	private AnimalRepository repository;
	
	@Autowired
	private WikiApiService wikiApiService;

	@GetMapping(path = "/breeds")
	public CollectionModel<Breed> retrieveAllBreeds() {
		Link selfLink = linkTo(methodOn(BreedController.class).retrieveAllBreeds()).withSelfRel()
				.withType(RequestMethod.GET.toString());
		Affordance create = afford(methodOn(BreedController.class).createBreed(null));

		Link post = linkTo(methodOn(BreedController.class).createBreed(null)).withSelfRel()
				.withType(RequestMethod.POST.toString());

		return CollectionModel.of(repository.findAll(), selfLink.andAffordance(create), post);
	}

	@GetMapping(path = "/breeds/{id}")
	public EntityModel<Breed> getBreedById(@PathVariable long id) {
		Optional<Breed> breed = repository.findById(id);
		// TODO throw error for breed == null
		// also create the exception to throw
		if (!breed.isPresent()) {
			throw new BreedNotFoundException(id);
		}

		Link selfLink = linkTo(methodOn(BreedController.class).getBreedById(id)).withSelfRel()
				.withType(RequestMethod.GET.toString());
		// Affordance delete = afford(methodOn(BreedController.class).deleteBreed(id));
		Affordance put = afford(methodOn(BreedController.class).updateBreed(id, null));
		Link coloursLink = linkTo(methodOn(BreedController.class).getColoursForBreed(id)).withRel("colours")
				.withType(RequestMethod.GET.toString());
		Link descendantOfLink = linkTo(methodOn(BreedController.class).getDescendantOfForBreed(id))
				.withRel("descendantOf").withType(RequestMethod.GET.toString());
		Link aggregateRoot = linkTo(methodOn(BreedController.class).retrieveAllBreeds()).withRel("breeds")
				.withType(RequestMethod.GET.toString());

		return EntityModel.of(breed.get(), selfLink.andAffordance(put), coloursLink, descendantOfLink, aggregateRoot);
	}

	@GetMapping(path = "/breeds/{id}/colours")
	public CollectionModel<String> getColoursForBreed(@PathVariable long id) {
		Optional<Breed> breed = repository.findById(id);

		if (!breed.isPresent()) {
			throw new BreedNotFoundException(id);
		}

		Link selfLink = linkTo(methodOn(BreedController.class).getColoursForBreed(id)).withSelfRel()
				.withType(RequestMethod.GET.toString());

		Link aggregateRoot = linkTo(methodOn(BreedController.class).retrieveAllBreeds()).withRel("breeds")
				.withType(RequestMethod.GET.toString());

		Link aggRootCol = linkTo(methodOn(BreedController.class).getColours()).withRel("colours")
				.withType(RequestMethod.GET.toString());

		return CollectionModel.of(Arrays.asList(breed.get().getColours()), selfLink, aggregateRoot, aggRootCol);
	}

	@GetMapping(path = "/breeds/colours")
	public CollectionModel<String> getColours() {
		List<Breed> breeds = repository.findAll();
		Set<String> colours = new TreeSet<>();

		for (Breed b : breeds) {
			colours.addAll(Arrays.asList(b.getColours()));
		}

		Link selfLink = linkTo(methodOn(BreedController.class).getColours()).withSelfRel()
				.withType(RequestMethod.GET.toString());

		Link aggregateRoot = linkTo(methodOn(BreedController.class).retrieveAllBreeds()).withRel("breeds")
				.withType(RequestMethod.GET.toString());

		return CollectionModel.of(colours, selfLink, aggregateRoot);
	}

	@GetMapping(path = "/breeds/{id}/descendantOf")
	public EntityModel<Breed> getDescendantOfForBreed(@PathVariable long id) {
		Optional<Breed> breed = repository.findById(id);

		if (!breed.isPresent()) {
			throw new BreedNotFoundException(id);
		}

		if (breed.get().getDescendantOfBreed() == null) {
			throw new NoDescendantFoundException("There is no descendant.");
		}

		Link selfLink = linkTo(methodOn(BreedController.class).getDescendantOfForBreed(id)).withSelfRel()
				.withType(RequestMethod.GET.toString());

		Link selfRoot = linkTo(methodOn(BreedController.class).getBreedById(id)).withRel("breed")
				.withType(RequestMethod.GET.toString());

		Link aggregateRoot = linkTo(methodOn(BreedController.class).retrieveAllBreeds()).withRel("breeds")
				.withType(RequestMethod.GET.toString());

		return EntityModel.of(breed.get().getDescendantOfBreed(), selfLink, selfRoot, aggregateRoot);
	}

	@GetMapping(path = "/breeds/description")
	public BreedModel getFieldDescriptions() {
		return new BreedModel("Unique identifier.", "Name of the breed.", "Number. Average life expectancy.",
				"Number. Average weight for the breed.", "Number. Average height for the breed.",
				"Array of strings. Describes common personality traits of the breed.",
				"Array of strings. Common fur colours and patterns.", "String. Describes the type of coat.",
				"String. Wiki-handle to the breeds wiki.", "String. Short description of the breed.",
				"String. Gender the traits apply to.", "Country the breed originates from.",
				"A classification of the breed based on common traits.", "Species name.", "The breeds ancestor.");
	}

	@PostMapping("/breeds")
	public ResponseEntity<Object> createBreed(@RequestBody Breed breed) {
		Breed savedBreed;
		try {
			savedBreed = repository.save(breed);
		} catch (Exception e) {
			throw new InvalidInputException("Invalid input. All fields must be present.");
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedBreed.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedBreed);
	}

	@DeleteMapping(path = "/breeds/{id}")
	@CacheEvict(cacheNames = "images")
	public void deleteBreed(@PathVariable long id) {
		repository.deleteById(id);
	}

	/**
	 * 
	 * @param id
	 * @param breed
	 * @return 201 CREATED or 200 OK response entity with location URI set.
	 */
	@PutMapping(path = "/breeds/{id}")
	public ResponseEntity<Object> updateBreed(@PathVariable long id, @RequestBody Breed breed) {

		Map<String, Integer> fieldStatus = breed.provideFieldStatus();
		try {
			Breed updatedBreed = repository.getOne(id);
			System.out.println(updatedBreed);
			breed.setId(id);
			fieldStatus.forEach((k, v) -> {
				if (v == 1) {
					if (k.equals("breedname")) {
						updatedBreed.setBreedname(breed.getBreedname());
					} else if (k.equals("lifeExpectancy")) {
						updatedBreed.setLifeExpectancy(breed.getLifeExpectancy());
					} else if (k.equals("weight")) {
						updatedBreed.setWeight(breed.getWeight());
					} else if (k.equals("height")) {
						updatedBreed.setHeight(breed.getHeight());
					} else if (k.equals("temperament")) {
						updatedBreed.setTemperament(breed.getTemperament());
					} else if (k.equals("colours")) {
						updatedBreed.setColours(breed.getColours());
					} else if (k.equals("coat")) {
						updatedBreed.setCoat(breed.getCoat());
					} else if (k.equals("wiki")) {
						updatedBreed.setWiki(breed.getWiki());
					} else if (k.equals("description")) {
						updatedBreed.setDescription(breed.getDescription());
					} else if (k.equals("gender")) {
						updatedBreed.setGender(breed.getGender());
					} else if (k.equals("countryOfOrigin")) {
						updatedBreed.setCountryOfOrigin(breed.getCountryOfOrigin());
					} else if (k.equals("classification")) {
						updatedBreed.setClassification(breed.getClassification());
					} else if (k.equals("species")) {
						updatedBreed.setSpecies(breed.getSpecies());
					} else if (k.equals("descendantOfBreed")) {
						updatedBreed.setDescendantOfBreed(breed.getDescendantOfBreed());
					}
				}
			});
			
			Breed newBreed = repository.save(updatedBreed);
			
			
			return ResponseEntity.ok(newBreed);
		} catch (Exception e) {
			fieldStatus.forEach((k, v) -> {
				if (v == 0 && !"descriptiondescendantOfBreedclassificationcountryOfOrigin".contains(k)) {
					throw new InvalidInputException(k + " is invalid");
				}
			});
			System.out.println(breed);
			Breed newBreed = repository.save(breed);
			URI location = ServletUriComponentsBuilder.fromPath("/breeds").path("/{id}").buildAndExpand(newBreed.getId())
					.toUri();
			return ResponseEntity.created(location).body(newBreed);
		}

	}
	
	@GetMapping("/breeds/{id}/picture")
	public ResponseEntity<byte[]> getPictureFromWiki(@PathVariable long id) {
		
		Optional<Breed> maybe = repository.findById(id);
		
		if (!maybe.isPresent()) {
			throw new BreedNotFoundException(id);
		}
		
		Breed breed = maybe.get();
		byte[] image = wikiApiService.getImage(breed.getWiki());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "image/jpeg");
		headers.add("Content-Disposition", "inline");
		
		// Jeli bitan Content-Disposition?
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
}






