package it.uniroma3.siw.siwcatering.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwcatering.model.Ingrediente;
import it.uniroma3.siw.siwcatering.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;
	
	@Transactional
	public Ingrediente save(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}
	
	public Collection<Ingrediente> findAll() {
		
		Collection<Ingrediente> result = new ArrayList<>();
		
		ingredienteRepository.findAll().forEach(result::add);
		
		return result;
		
	}
	
	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}
	
	public void delete(Ingrediente ingrediente) {
		ingredienteRepository.delete(ingrediente);
	}
	
	public void deleteById(Long id) {
		ingredienteRepository.deleteById(id);
	}

}
