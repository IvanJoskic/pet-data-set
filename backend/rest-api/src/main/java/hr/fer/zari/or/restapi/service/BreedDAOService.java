package hr.fer.zari.or.restapi.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import hr.fer.zari.or.restapi.entity.Breed;

@Repository
@Transactional
public class BreedDAOService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public long insert(Breed breed) {
		entityManager.persist(breed);
		return breed.getId();
	}
}
