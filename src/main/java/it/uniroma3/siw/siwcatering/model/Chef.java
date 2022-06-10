package it.uniroma3.siw.siwcatering.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Chef {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Size(min = 1, max = 30)
	private String nome;
	
	@Size(min = 1, max = 30)
	private String cognome;
	
	@Size(min = 1, max = 30)
	private String nazione;
	
	@OneToMany(mappedBy = "chef")
	private Collection<Buffet> buffet;
	
	public Chef() {
		buffet = new ArrayList<>();
	}
	
	public void addBuffet(Buffet buffet) {
		this.buffet.add(buffet);
	}
	
}
