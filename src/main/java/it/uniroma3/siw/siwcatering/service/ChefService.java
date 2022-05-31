package it.uniroma3.siw.siwcatering.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	private ChefRepository chefRepository;
	
	@Transactional
	public Chef save(Chef chef) {
		return chefRepository.save(chef);
	}
	
	public Collection<Chef> findAll() {
		
		Collection<Chef> result = new ArrayList<>();
		
		chefRepository.findAll().forEach(result::add);
		
		return result;
		
	}
	
	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}
	
	public void deleteById(Long id) {
		chefRepository.deleteById(id);
	}

}
