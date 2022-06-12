package it.uniroma3.siw.siwcatering.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.siwcatering.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {
	
	Collection<Piatto> findAllByOrderByNome();

}
