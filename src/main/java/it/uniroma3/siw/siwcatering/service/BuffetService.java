package it.uniroma3.siw.siwcatering.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.repository.BuffetRepository;

@Service
public class BuffetService {
	
	@Autowired
	private BuffetRepository buffetRepository;
	
	@Transactional
	public Buffet save(Buffet buffet) {
		return buffetRepository.save(buffet);
	}
	
	public Collection<Buffet> findAll() {
		
		Collection<Buffet> result = new ArrayList<>();
		
		buffetRepository.findAll().forEach(result::add);
		
		return result;
		
	}
	
	public Buffet findById(Long id) {
		return buffetRepository.findById(id).get();
	}
	
	public void delete(Buffet buffet) {
		buffetRepository.delete(buffet);
	}
	
	public void deleteById(Long id) {
		buffetRepository.deleteById(id);
	}

}
