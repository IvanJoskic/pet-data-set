package hr.fer.zari.or.restapi.service;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.zari.or.restapi.entity.Species;

public interface SpeciesRepository extends JpaRepository<Species, String>{

}
