package it.uniroma3.siw.siwcatering.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users") // Plural used to avoid reserved keyword "user"
@Getter @Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	private Credentials credentials;
	
	private String oAuthUniqueIdentifier;
	
	@Size(min = 3, max = 30)
	private String nome;
	
	@Size(min = 3, max = 30)
	private String cognome;
	
	public User() {
		
	}
	
	public User(String oAuthUniqueIdentifier, String nome, String cognome) {
		this.oAuthUniqueIdentifier = oAuthUniqueIdentifier;
		this.nome = nome;
		this.cognome = cognome;
	}
	
}
