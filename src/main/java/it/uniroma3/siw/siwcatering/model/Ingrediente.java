package it.uniroma3.siw.siwcatering.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min = 1, max = 30)
	private String nome;
	
	@Size(min = 1, max = 100)
	private String descrizione;
	
	@Size(min = 1, max = 30)
	private String origine;

}
