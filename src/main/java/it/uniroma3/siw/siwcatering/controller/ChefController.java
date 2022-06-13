package it.uniroma3.siw.siwcatering.controller;

import java.util.Collection;

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

import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;

	@GetMapping("/chef")
	public String getElencoChef(Model model) {
		
		Collection<Chef> chefs = chefService.findAllByOrderByCognome();
		
		model.addAttribute("chefs", chefs);
		
		return "elenco-chef";
		
	}
	
	@GetMapping("/chef/{chefId}")
	public String getChef(@PathVariable("chefId") Long chefId,
			Model model) {
		
		Chef chef = chefService.findById(chefId);
		
		model.addAttribute("chef", chef);
		
		return "dettagli-chef";
		
	}
	
	@GetMapping("/admin/inserisci-chef")
	public String getInserisciChefView(Model model) {
		
		model.addAttribute("chef", new Chef());
		
		return "inserisci-chef";
		
	}
	
	@PostMapping("/admin/chef")
	public String createChef(@Valid @ModelAttribute("chef") Chef chef,
			BindingResult chefBindingResult,
			Model model) {
		
		if (!chefBindingResult.hasErrors()) {
			Chef savedChef = chefService.save(chef);
			return "redirect:/chef/" + savedChef.getId();
		}
		
		return "inserisci-chef";
		
	}
	
	@GetMapping("/admin/chef/{chefId}/modifica")
	public String getModificaChefView(@PathVariable("chefId") Long chefId,
			Model model) {
		
		model.addAttribute("chef", chefService.findById(chefId));
		
		return "modifica-chef";
		
	}
	
	@PostMapping("/admin/chef/{chefId}/modifica")
	public String updateChef(@Valid @ModelAttribute("chef") Chef chef,
			BindingResult chefBindingResult,
			RedirectAttributes redirectAttributes) {
		
		if (!chefBindingResult.hasErrors()) {
			chefService.save(chef);
			redirectAttributes.addFlashAttribute("successFlashMessages", "Modifiche allo chef salvate.");
			return "redirect:/chef/" + chef.getId();
		}
		
		return "modifica-chef";
	}
	
	@GetMapping("/admin/chef/{chefId}/cancella")
	public String deleteChef(@PathVariable("chefId") Long chefId,
			RedirectAttributes redirectAttributes) {
		
		chefService.deleteById(chefId);
		
		redirectAttributes.addFlashAttribute("successFlashMessages", "Chef cancellato con successo.");
		
		return "redirect:/admin";
		
	}
	
}
