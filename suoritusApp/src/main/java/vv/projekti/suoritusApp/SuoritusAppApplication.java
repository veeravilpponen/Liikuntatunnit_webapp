package vv.projekti.suoritusApp;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

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

@SpringBootApplication
public class SuoritusAppApplication extends WebMvcConfigurerAdapter{

	private static final Logger log = LoggerFactory.getLogger(SuoritusAppApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SuoritusAppApplication.class, args);
	}
	
	//KIELEN VAIHTO
	@Bean
	public LocaleResolver localeResolver(){
	       SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	       localeResolver.setDefaultLocale(Locale.ENGLISH);
	       return  localeResolver;
	   }
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	    localeChangeInterceptor.setParamName("lang");
	    return localeChangeInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
	    registry.addInterceptor(localeChangeInterceptor());
	}
	
	
	//TESTIDATAA
	@Bean
	public CommandLineRunner suoritusDemo(SuoritusRepository suoritusRepository,ViikonpaivaRepository paivaRepository, PaikkaRepository paikkaRepository, TarjoajaRepository tarjoajaRepository, UserRepository userRepository) {
		return (args) -> {
			
			log.info("tallenna käyttäjät");
			//ajetaan tietokantaan käyttäjiä
			// käyttäjänimi = salasana
			userRepository.save(new User("veera", "$2a$10$x6X0Fs5DibM5Dj0aY6VPQ.66yrL405zWOOJqth/MZ5mw/.oLv7Ym6", "USER", "veera@veera.com"));
			userRepository.save(new User("user1", "$2a$10$gOZBXoElzBYwEu6E.FrYlulMI2SdsbPqEktLvrGAEyUVlgKIE4lfG", "USER", "user1@1.com"));
			userRepository.save(new User("user2", "$2a$10$dipRrajxRQgeNShTmFrqH.YMjQrEbszjTzFiTjpHoeIKypijcYz9e", "USER", "user2@2.com"));
			userRepository.save(new User("admin", "$2a$10$QJbRqEQTo0uHF4U3.ac8a.PjUCNCb4pEsiGEIpEgvKyyV9.o6wrzS", "ADMIN", "admin@admin.com"));
			
			log.info("tallenna päivät");
			//viikonpaivat
			paivaRepository.save(new Viikonpaiva("maanantai"));
			paivaRepository.save(new Viikonpaiva("tiistai"));
			paivaRepository.save(new Viikonpaiva("keskiviikko"));
			paivaRepository.save(new Viikonpaiva("torstai"));
			paivaRepository.save(new Viikonpaiva("perjantai"));
			paivaRepository.save(new Viikonpaiva("lauantai"));
			paivaRepository.save(new Viikonpaiva("sunnuntai"));
			
			log.info("tallenna paikat");
			//ajetaan tietokantaan paikkoja
			paikkaRepository.save(new Paikka("Kampin liikuntakeskus"));
			paikkaRepository.save(new Paikka("Pirkkolan urheilukeskus"));
			paikkaRepository.save(new Paikka("Metropolia Bulevardi"));
			
			log.info("tallenna tarjoajat");
			//ajetaan tietokantaan tarjoajia
			tarjoajaRepository.save(new Tarjoaja("Nyt-liikunta"));
			tarjoajaRepository.save(new Tarjoaja("Zonesports"));
			
			//ajetaan tietokantaan suorituksia
			suoritusRepository.save(new Suoritus("Baletin alkeet", 326, "14.00", "15.00", paivaRepository.findByNimi("perjantai").get(0), paikkaRepository.findByNimi("Kampin liikuntakeskus").get(0), tarjoajaRepository.findByNimi("Nyt-liikunta").get(0))); 
			suoritusRepository.save(new Suoritus("Kahvakuula", 524, "15.00", "16.00",  paivaRepository.findByNimi("keskiviikko").get(0), paikkaRepository.findByNimi("Pirkkolan urheilukeskus").get(0), tarjoajaRepository.findByNimi("Nyt-liikunta").get(0))); 
			suoritusRepository.save(new Suoritus("Vinyasa Flow Jooga", 326, "17.00", "18.00", paivaRepository.findByNimi("tiistai").get(0), paikkaRepository.findByNimi("Metropolia Bulevardi").get(0), tarjoajaRepository.findByNimi("Zonesports").get(0))); 
			suoritusRepository.save(new Suoritus("Show-tanssi", 531, "15.00", "16.00", paivaRepository.findByNimi("maanantai").get(0), paikkaRepository.findByNimi("Kampin liikuntakeskus").get(0), tarjoajaRepository.findByNimi("Nyt-liikunta").get(0))); 
			suoritusRepository.save(new Suoritus("Tankotanssi", 341,"12.00", "12.00", paivaRepository.findByNimi("lauantai").get(0), paikkaRepository.findByNimi("Kampin liikuntakeskus").get(0), tarjoajaRepository.findByNimi("Nyt-liikunta").get(0))); 
			suoritusRepository.save(new Suoritus("xxx", 542, "13.30", "14.30", paivaRepository.findByNimi("torstai").get(0), paikkaRepository.findByNimi("Kampin liikuntakeskus").get(0), tarjoajaRepository.findByNimi("Nyt-liikunta").get(0))); 
			suoritusRepository.save(new Suoritus("yyy", 234, "19.00", "20.00", paivaRepository.findByNimi("sunnuntai").get(0), paikkaRepository.findByNimi("Kampin liikuntakeskus").get(0), tarjoajaRepository.findByNimi("Nyt-liikunta").get(0))); 
			
			log.info("hae suoritukset");
			for (Suoritus suoritus : suoritusRepository.findAll()) {
				log.info(suoritus.toString());
			}

		};
	}
	
}
