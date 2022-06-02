package it.uniroma3.siw.siwcatering.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Piatto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String descrizione;
	
	@ManyToOne
	private Buffet buffet;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "piatto_id")
	private Collection<Ingrediente> ingredienti;
	
	public void addIngrediente(Ingrediente ingrediente) {
		ingredienti.add(ingrediente);
	}
	
	public void removeIngrediente(Ingrediente ingrediente) {
		ingredienti.remove(ingrediente);
	}

}
