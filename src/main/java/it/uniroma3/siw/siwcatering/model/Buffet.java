package it.uniroma3.siw.siwcatering.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	private String nome;
	
	private String descrizione;
	
	@OneToMany(mappedBy = "buffet", fetch = FetchType.EAGER)
	//@JoinColumn(name = "buffet_id")
	private Collection<Piatto> piatti;
	
	public void addPiatto(Piatto piatto) {
		piatti.add(piatto);
	}
	
	public void removePiatto(Piatto piatto) {
		piatti.remove(piatto);
	}
	
}
