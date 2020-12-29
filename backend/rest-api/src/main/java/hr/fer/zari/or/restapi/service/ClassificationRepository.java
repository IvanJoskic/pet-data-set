package hr.fer.zari.or.restapi.service;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.zari.or.restapi.entity.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, String>{

}
