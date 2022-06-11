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
import it.uniroma3.siw.siwcatering.model.Chef;
import it.uniroma3.siw.siwcatering.service.BuffetService;
import it.uniroma3.siw.siwcatering.service.ChefService;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/buffet")
	public String getElencoBuffet(Model model) {
		
		model.addAttribute("buffets", buffetService.findAll());
		
		return "elenco-buffet";
		
	}
	
	@GetMapping("/buffet/{buffetId}")
	public String getBuffet(@PathVariable("buffetId") Long buffetId,
			Model model) {
		
		model.addAttribute("buffet", buffetService.findById(buffetId));
		
		return "dettagli-buffet";
		
	}
	
	@GetMapping("/admin/chef/{chefId}/buffet/nuovo")
	public String getInserisciBuffetView(@PathVariable("chefId") Long chefId,
			Model model) {
		
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("chef", chefService.findById(chefId));
		
		return "inserisci-buffet";
		
	}
	
	@PostMapping("/admin/chef/{chefId}/buffet")
	public String createBuffet(@Valid @ModelAttribute("buffet") Buffet buffet,
			BindingResult buffetBindingResult,
			@PathVariable("chefId") Long chefId,
			Model model) {
		
		Chef chef = chefService.findById(chefId);
		
		if (!buffetBindingResult.hasErrors()) {
			
			buffet.setChef(chef);
			
			Buffet savedBuffet = buffetService.save(buffet);
			
			chef.addBuffet(savedBuffet);
			chefService.save(chef);
			
			return "redirect:/buffet/" + savedBuffet.getId();
			
		}
		
		model.addAttribute("chef", chef);
		
		return "inserisci-buffet";
		
	}
	
	@GetMapping("/admin/buffet/{buffetId}/modifica")
	public String getModificaBuffetView(@PathVariable("buffetId") Long buffetId,
			Model model) {
		
		model.addAttribute("buffet", buffetService.findById(buffetId));
		
		return "modifica-buffet";
		
	}
	
	@PostMapping("/admin/buffet/{buffetId}/modifica")
	public String updateBuffet(@Valid @ModelAttribute("buffet") Buffet buffet,
			BindingResult buffetBindingResult) {
		
		if (!buffetBindingResult.hasErrors()) {
			buffetService.save(buffet);
			return "redirect:/buffet/" + buffet.getId();
		}
		
		return "modifica-buffet";
		
	}
	
	@GetMapping("/admin/buffet/{buffetId}/cancella")
	public String deleteBuffet(@PathVariable("buffetId") Long buffetId,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		Buffet buffet = buffetService.findById(buffetId);
		
		buffetService.delete(buffet);
		
		redirectAttributes.addFlashAttribute("successFlashMessages", "Buffet cancellato con successo.");
		
		String refererUrl = request.getHeader("referer");
		if (refererUrl.contains("/admin")) {
			return "redirect:/admin";
		}
		return "redirect:/chef/" + buffet.getChef().getId();
		
	}

}
