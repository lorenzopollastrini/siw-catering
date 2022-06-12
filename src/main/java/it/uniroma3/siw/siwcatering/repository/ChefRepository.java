package it.uniroma3.siw.siwcatering.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.siwcatering.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {
	
	public Collection<Chef> findAllByOrderByCognome();

}
