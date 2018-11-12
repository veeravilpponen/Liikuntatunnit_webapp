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

import vv.projekti.suoritusApp.model.Paikka;
import vv.projekti.suoritusApp.model.PaikkaRepository;

@Controller
public class PaikkaController {
	
	// lisätään luokkaan repositoryt attribuutteina
	// attribuutilta voi pyytää tietokantakäsittelyjä, esim delete, save
	
	@Autowired
	private PaikkaRepository paikkaRepository;
  	
	// näytetään paikkojen tiedot tietokannasta
 	@GetMapping({"/listaapaikat"})
 	public String listaaPaikat(Model model) {
 		model.addAttribute("paikat", paikkaRepository.findAll());
 		return "paikkalista"; 
 	}
 	
 	// REST-palvelu, hae kaikki paikat
     @RequestMapping(value="/paikat", method = RequestMethod.GET)
     // @ResponseBody kertoo, että pitää palauttaa json-dataa
     public @ResponseBody List<Paikka> listaaPaikatRest() {		
         return (List<Paikka>) paikkaRepository.findAll();
     }    

 	// REST-palvelu, hae paikka tietyllä id:llä
     @RequestMapping(value="/paikat/{id}", method = RequestMethod.GET)
     public @ResponseBody Optional<Paikka> etsiPaikkaRest(@PathVariable("id") Long paikkaId) {
     	return paikkaRepository.findById(paikkaId);
     }      
 	
   	
   	// palauttaa tyhjän lomakkeen, jolla voidaan lisätä uusi paikka
  	@RequestMapping(value="/lisaapaikka")
      public String lisaaPaikka(Model model){
      	model.addAttribute("paikka", new Paikka());
          return "lisaapaikka";
      }
   	
  	// tallettaa paikan
     @PostMapping("/tallennapaikka")
     public String tallennaPaikka(Paikka paikka){
         paikkaRepository.save(paikka);
         return "redirect:listaapaikat";
     }
     
     // päivitetään paikan tiedot
   	@RequestMapping(value="/paivitapaikka/{id}")
   	public String muokkaaPaikkaa(@PathVariable("id") Long paikkaId, Model model) {
   		//haetaan valitun paikan tiedot id:n perusteella
   		model.addAttribute("paivpaikka", paikkaRepository.findById(paikkaId));
   		return "paivitapaikka";
   	}
   	
   	// metodi poistaa tietokannasta valitun paikan id:n perusteella
  	@GetMapping("/poistapaikka/{id}")
  	public String poistaPaikka(@PathVariable("id") Long paikkaId) {
  		paikkaRepository.deleteById(paikkaId);
  		//mene uuteen osoitteeseen
  		return "redirect:../listaapaikat";
  	}
  	
}
