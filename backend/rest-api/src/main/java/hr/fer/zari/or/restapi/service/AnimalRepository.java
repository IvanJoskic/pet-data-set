package hr.fer.zari.or.restapi.service;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.zari.or.restapi.entity.Breed;

public interface AnimalRepository extends JpaRepository<Breed, Long>{

}
