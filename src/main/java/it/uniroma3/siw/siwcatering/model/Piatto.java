package it.uniroma3.siw.siwcatering.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Piatto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min = 1, max = 30)
	private String nome;
	
	@Size(min = 0, max = 100)
	private String descrizione;
	
	@Size(min = 0, max = 1000)
	private String urlImmagine;
	
	@ManyToOne
	private Buffet buffet;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "piatto_id")
	private Collection<Ingrediente> ingredienti;
	
	public void addIngrediente(Ingrediente ingrediente) {
		ingredienti.add(ingrediente);
	}

}
