package it.uniroma3.siw.siwcatering.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.siwcatering.model.User;
import it.uniroma3.siw.siwcatering.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	
	public User findByUsername(String username) {
		
		Optional<User> user = userRepository.findByUsername(username);
		
		if (user.isPresent()) return user.get();
		
		return null;
	}
	
}