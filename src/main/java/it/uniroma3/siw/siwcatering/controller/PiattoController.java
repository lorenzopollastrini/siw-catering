package it.uniroma3.siw.siwcatering.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.uniroma3.siw.siwcatering.model.Buffet;
import it.uniroma3.siw.siwcatering.model.Piatto;
import it.uniroma3.siw.siwcatering.service.BuffetService;
import it.uniroma3.siw.siwcatering.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private BuffetService buffetService;
	
	@GetMapping("/piatto")
	public String getElencoPiatti(Model model) {
		
		model.addAttribute("piatti", piattoService.findAllByOrderByNome());
		
		return "elenco-piatti";
		
	}
	
	@GetMapping("/piatto/{piattoId}")
	public String getPiatto(@PathVariable("piattoId") Long piattoId,
			Model model) {
		
		model.addAttribute("piatto", piattoService.findById(piattoId));
		
		return "dettagli-piatto";
		
	}
	
	@GetMapping("/admin/buffet/{buffetId}/piatto/nuovo")
	public String getInserisciPiattoView(@PathVariable("buffetId") Long buffetId,
			Model model) {
		
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("buffet", buffetService.findById(buffetId));
		
		return "inserisci-piatto";
		
	}
	
	@PostMapping("/admin/buffet/{buffetId}/piatto")
	public String createPiatto(@Valid @ModelAttribute("piatto") Piatto piatto,
			BindingResult piattoBindingResult,
			@PathVariable("buffetId") Long buffetId,
			Model model) {
		
		Buffet buffet = buffetService.findById(buffetId);
		
		if (!piattoBindingResult.hasErrors()) {
			
			piatto.setBuffet(buffet);
			
			Piatto savedPiatto = piattoService.save(piatto);
			
			buffet.addPiatto(savedPiatto);
			buffetService.save(buffet);
			
			return "redirect:/piatto/" + savedPiatto.getId();
			
		}
		
		model.addAttribute("buffet", buffet);
		
		return "inserisci-piatto";
		
	}
	
	@GetMapping("/admin/piatto/{piattoId}/modifica")
	public String getModificaPiattoView(@PathVariable("piattoId") Long piattoId,
			Model model) {
		
		model.addAttribute("piatto", piattoService.findById(piattoId));
		
		return "modifica-piatto";
		
	}
	
	@PostMapping("/admin/piatto/{piattoId}/modifica")
	public String updatePiatto(@Valid @ModelAttribute("piatto") Piatto piatto,
			BindingResult piattoBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (!piattoBindingResult.hasErrors()) {
			Piatto savedPiatto = piattoService.save(piatto);
			redirectAttributes.addFlashAttribute("successFlashMessages", "Modifiche al piatto salvate.");
			return "redirect:/piatto/" + savedPiatto.getId();
		}
		
		return "modifica-piatto";
		
	}
	
	@GetMapping("/admin/piatto/{piattoId}/cancella")
	public String deletePiatto(@PathVariable("piattoId") Long piattoId,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		Piatto piatto = piattoService.findById(piattoId);
		
		piattoService.delete(piatto);
		
		redirectAttributes.addFlashAttribute("successFlashMessages", "Piatto cancellato con successo.");
		
		String refererUrl = request.getHeader("referer");
		if (refererUrl.contains("/admin")) {
			return "redirect:/admin";
		}
		return "redirect:/buffet/" + piatto.getBuffet().getId();
		
	}

}
