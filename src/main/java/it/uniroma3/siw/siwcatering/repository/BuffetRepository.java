package it.uniroma3.siw.siwcatering.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.siwcatering.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
	
	Collection<Buffet> findAllByOrderByNome();

}
