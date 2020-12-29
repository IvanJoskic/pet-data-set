package hr.fer.zari.or.restapi.service;

import org.springframework.data.jpa.repository.JpaRepository;

import hr.fer.zari.or.restapi.entity.Country;

public interface CountryRepositroy extends JpaRepository<Country, String>{

}
