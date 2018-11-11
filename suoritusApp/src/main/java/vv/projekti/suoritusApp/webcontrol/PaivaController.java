package vv.projekti.suoritusApp.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vv.projekti.suoritusApp.model.ViikonpaivaRepository;

@Controller
public class PaivaController {
	
	@Autowired
	private ViikonpaivaRepository paivaRepository;
	
	// näytetään paivien tiedot tietokannasta
	@GetMapping({"/listaapaivat", "/"})
	public String listaaPaivat(Model model) {
		model.addAttribute("paivat", paivaRepository.findAll());
		return "paivalista";
	}
	
	
}
