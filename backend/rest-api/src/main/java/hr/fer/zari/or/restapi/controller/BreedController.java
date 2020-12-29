package hr.fer.zari.or.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.zari.or.restapi.entity.Breed;
import hr.fer.zari.or.restapi.service.AnimalRepository;

@RestController
public class BreedController {
	@Autowired
	private AnimalRepository repository;
	
	@GetMapping(path = "/breeds")
	public List<Breed> retrieveAllBreeds() {
		return repository.findAll();
	}
	
}
