package it.uniroma3.siw.siwcatering.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.siwcatering.model.Piatto;
import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.service.PiattoService;
import it.uniroma3.siw.siwcatering.service.BuffetService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private BuffetService buffetService;
	
	@GetMapping("/admin/buffet/{buffetId}/piatto/nuovo")
	public String getInserisciPiattoView(@PathVariable("buffetId") Long buffetId,
			Model model) {
		
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("buffet", buffetService.findById(buffetId));
		
		return "inserisci-piatto";
		
	}
	
	@PostMapping("/admin/buffet/{buffetId}/piatto")
	public String inserisciPiatto(@Valid @ModelAttribute("piatto") Piatto piatto,
			BindingResult piattoBindingResult,
			@PathVariable("buffetId") Long buffetId) {
		
		if (!piattoBindingResult.hasErrors()) {
			
			Buffet buffet = buffetService.findById(buffetId);
			
			Piatto savedPiatto = piattoService.save(piatto);
			
			buffet.addPiatto(savedPiatto);
			buffetService.save(buffet);
			
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/piatto/{piattoId}/modifica")
	public String getModificaPiattoView(@PathVariable("piattoId") Long piattoId,
			Model model) {
		
		model.addAttribute("piatto", piattoService.findById(piattoId));
		
		return "modifica-piatto";
		
	}
	
	@PostMapping("/admin/piatto/{piattoId}/modifica")
	public String modificaPiatto(@Valid @ModelAttribute("piatto") Piatto piatto,
			BindingResult piattoBindingResult) {
		
		if (!piattoBindingResult.hasErrors()) {
			piattoService.save(piatto);
		}
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin/buffet/{buffetId}/piatto/{piattoId}/cancella")
	public String cancellaPiatto(@PathVariable("buffetId") Long buffetId,
			@PathVariable("piattoId") Long piattoId) {
		
		Piatto piatto = piattoService.findById(piattoId);
		Buffet buffet = buffetService.findById(buffetId);
		
		buffet.removePiatto(piatto);
		buffetService.save(buffet);
		
		piattoService.delete(piatto);
		
		return "redirect:/admin";
		
	}

}
