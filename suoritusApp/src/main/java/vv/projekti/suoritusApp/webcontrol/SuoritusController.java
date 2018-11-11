package vv.projekti.suoritusApp.webcontrol;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vv.projekti.suoritusApp.model.PaikkaRepository;
import vv.projekti.suoritusApp.model.Suoritus;
import vv.projekti.suoritusApp.model.SuoritusRepository;
import vv.projekti.suoritusApp.model.TarjoajaRepository;
import vv.projekti.suoritusApp.model.Viikonpaiva;
import vv.projekti.suoritusApp.model.ViikonpaivaRepository;

@Controller
public class SuoritusController {
	
	// lisätään luokkaan repositoryt attribuutteina
	// attribuutilta voi pyytää tietokantakäsittelyjä, esim delete, save
	@Autowired
	private SuoritusRepository suoritusRepository;
	
	@Autowired
	private ViikonpaivaRepository paivaRepository;
	
	@Autowired
	private PaikkaRepository paikkaRepository;
	
	@Autowired
	private TarjoajaRepository tarjoajaRepository;
	
	
	//viikonpäivien mukaan suoritusten listaus
	@RequestMapping(value="/listaatunnit/{id}", method = RequestMethod.GET)
    public String paivienTunnit(@PathVariable("id") Long viikonpaivaId, Model model){
		
		Viikonpaiva viikonpaiva = paivaRepository.findById(viikonpaivaId).get();
    	model.addAttribute("viikonpaiva", viikonpaiva);
    	
    	List<Suoritus> suoritukset = suoritusRepository.findByViikonpaiva(viikonpaiva);
    	model.addAttribute("suoritukset", suoritukset);
        return "tuntilista";
    }
    
	// näytetään suoritusten tiedot tietokannasta
	@GetMapping("/listaasuoritukset")
	public String listaaSuoritukset(Model model) {
		model.addAttribute("suoritukset", suoritusRepository.findAll());
		model.addAttribute("paivat", paivaRepository.findAll());
		return "suorituslista"; //palauttaa stringinä suorituslista.html -templaten nimen,
        //niin servlet tietää, että kutsuu thymeleafin muodostamaan html:ää
	}
	
	// REST-palvelu, hae kaikki suoritukset
    @RequestMapping(value="/suoritukset", method = RequestMethod.GET)
    // @ResponseBody kertoo, että pitää palauttaa json-dataa
    public @ResponseBody List<Suoritus> listaaSuorituksetRest() {		
    	// palauttaa repositorysta haetut suoritukset, muuntuu automaattisesti json:ksi
        return (List<Suoritus>) suoritusRepository.findAll();
    }    

	// REST-palvelu, hae suoritus tietyllä id:llä
    @RequestMapping(value="/suoritukset/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Suoritus> etsiSuoritusRest(@PathVariable("id") Long suoritusId) {
    	return suoritusRepository.findById(suoritusId);
    }      
	
	// metodi poistaa tietokannasta valitun suorituksen id:n perusteella
	@GetMapping("/poistasuoritus/{id}")
	public String poistaSuoritus(@PathVariable("id") Long suoritusId) {
		suoritusRepository.deleteById(suoritusId);
		//mene uuteen osoitteeseen
		return "redirect:../listaasuoritukset";
	}
	
	// palauttaa tyhjän lomakkeen, jolla voidaan lisätä uusi suoritus
	@RequestMapping(value="/lisaasuoritus")
    public String lisaaSuoritus(Model model){
    	model.addAttribute("suoritus", new Suoritus());
    	// modelille voi välittää useamman tiedon
    	// mahdolliset paikat pitää hakea tietokannasta
    	model.addAttribute("paivat", paivaRepository.findAll());
    	model.addAttribute("paikat", paikkaRepository.findAll());
    	model.addAttribute("tarjoajat", tarjoajaRepository.findAll());
        return "lisaasuoritus";
    }
	
	// tallettaa suorituksen
    @PostMapping("/tallennasuoritus")
    public String tallennaSuoritus(Suoritus suoritus){
        suoritusRepository.save(suoritus);
        return "redirect:listaasuoritukset";
    }
    
    // päivitetään suorituksen tiedot
  	@RequestMapping(value="/paivitasuoritus/{id}")
  	public String muokkaaSuoritusta(@PathVariable("id") Long suoritusId, Model model) {
  		//haetaan valitun suorituksen tiedot id:n perusteella
  		model.addAttribute("paivsuoritus", suoritusRepository.findById(suoritusId));
  		model.addAttribute("paikat", paikkaRepository.findAll());
  		model.addAttribute("tarjoajat", tarjoajaRepository.findAll());
  		model.addAttribute("paivat", paivaRepository.findAll());
  		return "paivitasuoritus";
  	}
  	
  	// kirjautuminen sovellukseen
  	@RequestMapping(value="/login")
	public String login() {
		return "kirjautuminen";
	}
  	
}
