package vv.projekti.suoritusApp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import vv.projekti.suoritusApp.model.Paikka;
import vv.projekti.suoritusApp.model.PaikkaRepository;
import vv.projekti.suoritusApp.model.Suoritus;
import vv.projekti.suoritusApp.model.SuoritusRepository;
import vv.projekti.suoritusApp.model.Tarjoaja;
import vv.projekti.suoritusApp.model.TarjoajaRepository;
import vv.projekti.suoritusApp.model.User;
import vv.projekti.suoritusApp.model.UserRepository;
import vv.projekti.suoritusApp.model.Viikonpaiva;
import vv.projekti.suoritusApp.model.ViikonpaivaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {

	EntityManager entityManager;
	
	@Autowired
    private SuoritusRepository suoritusRepository;
	
	@Autowired
    private PaikkaRepository paikkaRepository;
	
	@Autowired
    private ViikonpaivaRepository paivaRepository;
	
	@Autowired
    private TarjoajaRepository tarjoajaRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	//SuoritusRepository
	
	//testaa pystytäänkö luomaan suoritus
	@Test
    public void createNewSuoritus() {
    	Suoritus suoritus = new Suoritus("Baletin alkeet", 326, "14.00", "15.00", paivaRepository.findByNimi("perjantai").get(0), paikkaRepository.findByNimi("Kampin liikuntakeskus").get(0), tarjoajaRepository.findByNimi("Nyt-liikunta").get(0));
    	suoritusRepository.save(suoritus);
    	assertThat(suoritus.getId()).isNotNull();
    }

	//testaa pystytäänkö etsimään suoritus
	@Test
	public void findByLajiShouldReturnSuoritus() {
	    List<Suoritus> suoritus = suoritusRepository.findByLaji("Baletin alkeet");
	        
        assertThat(suoritus).hasSize(1);
        assertThat(suoritus.get(0).getAlkamisaika()).isEqualTo("14.00");
    }
	
	//testaa pystytäänkö poistamaan suoritus
	@Test
	public void deleteBook() {
		Suoritus suoritus = new Suoritus("Hiphop", 326, "14.00", "15.00", paivaRepository.findByNimi("perjantai").get(0),paikkaRepository.findByNimi("Kampin liikuntakeskus").get(0), tarjoajaRepository.findByNimi("Nyt-liikunta").get(0));
    	suoritusRepository.save(suoritus);
		Long id = suoritus.getId();
		suoritusRepository.deleteById(id);
		Optional<Suoritus> optsuoritus = suoritusRepository.findById(id);
		assertThat(optsuoritus).isEmpty();
	}
	
	// PAikkaRepository
	
	//testaa pystytäänkö luomaan paikka
	@Test
    public void createNewPaikka() {
    	Paikka paikka = new Paikka("Kampin liikuntakeskus");
    	paikkaRepository.save(paikka);
    	assertThat(paikka.getPaikkaId()).isNotNull();
    }
	
	//testaa pystytäänkö etsimään paikka
	@Test
	public void findByNimiShouldReturnPaikka() {
	    List<Paikka> paikat = paikkaRepository.findByNimi("Kampin liikuntakeskus");
	        
        assertThat(paikat).hasSize(1);
    }
	
	//testaa pystytäänkö poistamaan kategoria
	@Test
	public void deletePaikka() {
		Paikka paikka = new Paikka("Metropolia Leppävaara");
		paikkaRepository.save(paikka);
		Long id = paikka.getPaikkaId();
		paikkaRepository.deleteById(id);
		Optional<Paikka> optpaikka = paikkaRepository.findById(id);
		assertThat(optpaikka).isEmpty();
	}
	
	// TarjoajaRepository
	
	//testaa pystytäänkö luomaan tarjoaja
	@Test
    public void createNewTarjoaja() {
		Tarjoaja tarjoaja = new Tarjoaja("Hopsanssaa");
		tarjoajaRepository.save(tarjoaja);
    	assertThat(tarjoaja.getTarjoajaId()).isNotNull();
    }
	
	//testaa pystytäänkö etsimään tarjoaja
	@Test
	public void findByNimiShouldReturnTarjoaja() {
	    List<Tarjoaja> tarjoajat = tarjoajaRepository.findByNimi("Nyt-liikunta");
	        
        assertThat(tarjoajat).hasSize(1);
    }
	
	//testaa pystytäänkö poistamaan tarjoaja
	@Test
	public void deleteTarjoaja() {
		Tarjoaja tarjoaja = new Tarjoaja("Heisulivei");
		tarjoajaRepository.save(tarjoaja);
		Long id = tarjoaja.getTarjoajaId();
		tarjoajaRepository.deleteById(id);
		Optional<Tarjoaja> opttarjoaja = tarjoajaRepository.findById(id);
		assertThat(opttarjoaja).isEmpty();
	}
	
	
	// PaivaRepository
	
	//testaa pystytäänkö luomaan tarjoaja
	@Test
    public void createNewPaiva() {
		Viikonpaiva viikonpaiva = new Viikonpaiva("perjantai");
		paivaRepository.save(viikonpaiva);
    	assertThat(viikonpaiva.getViikonpaivaId()).isNotNull();
    }
	
	//testaa pystytäänkö etsimään tarjoaja
	@Test
	public void findByNimiShouldReturnPaiva() {
	    List<Viikonpaiva> viikonpaivat = paivaRepository.findByNimi("torstai");
	        
        assertThat(viikonpaivat).hasSize(1);
    }
	
	//UserRepository
	
	//testaa pystytäänkö luomaan käyttäjä
	@Test
    public void createNewUser() {
    	User user = new User("user3", "$2a$10$gOZBXoElzBYwEu6E.FrYlulMI2SdsbPqEktLvrGAEyUVlgKIE4lfG", "USER", "user3@3.com");
    	userRepository.save(user);
    	assertThat(user.getId()).isNotNull();
    }

	//testaa pystytäänkö etsimään käyttäjää
	@Test
	public void findByUsernameShouldReturnUser() {
	    User user = userRepository.findByUsername("user1");
	    
        assertThat(user.getUsername()).isEqualTo("user1");
    }

	//testaa pystytäänkö poistamaan käyttäjä
		@Test
		public void deleteUser() {
			User user = new User("user4", "$2a$10$gOZBXoElzBYwEu6E.FrYlulMI2SdsbPqEktLvrGAEyUVlgKIE4lfG", "USER", "user4@4.com");
			userRepository.save(user);
			Long id = user.getId();
			userRepository.deleteById(id);
			Optional<User> optuser = userRepository.findById(id);
			assertThat(optuser).isEmpty();
		}
		
}
