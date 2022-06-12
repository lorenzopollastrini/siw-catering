package it.uniroma3.siw.siwcatering.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwcatering.model.Piatto;
import it.uniroma3.siw.siwcatering.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository;
	
	@Transactional
	public Piatto save(Piatto piatto) {
		return piattoRepository.save(piatto);
	}
	
	public Collection<Piatto> findAllByOrderByNome() {
		return piattoRepository.findAllByOrderByNome();
	}
	
	public Piatto findById(Long id) {
		return piattoRepository.findById(id).get();
	}
	
	public void delete(Piatto piatto) {
		piattoRepository.delete(piatto);
	}
	
	public void deleteById(Long id) {
		piattoRepository.deleteById(id);
	}

}
