package it.uniroma3.siw.siwcatering.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.siwcatering.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.credentials.username = :username")
	public Optional<User> findByUsername(@Param("username") String username);
	
}
