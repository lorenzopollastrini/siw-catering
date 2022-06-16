package it.uniroma3.siw.siwcatering.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Buffet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Chef chef;
	
	@Size(min = 1, max = 30)
	private String nome;
	
	@Size(min = 0, max = 100)
	private String descrizione;
	
	@OneToMany(mappedBy = "buffet", cascade = CascadeType.REMOVE)
	private Collection<Piatto> piatti;
	
	public void addPiatto(Piatto piatto) {
		piatti.add(piatto);
	}
	
}
