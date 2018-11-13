package vv.projekti.suoritusApp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Suoritus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String laji;	// urheilulaji
	
	@Min(10)
	@Max(1000)
	private int kulutus; 	// energiankulutus kcal
	
	@NotEmpty
	private String alkamisaika;

	@NotEmpty
	private String loppumisaika;
	
	@ManyToOne  	// Suoritus(*) - Viikonpäivä(1)
    @JsonIgnore 	
    @JoinColumn(name = "viikonpaivaId")
	private Viikonpaiva viikonpaiva;
	
	@ManyToOne  	// Suoritus(*) - Paikka(1)
    @JsonIgnore 	// kun muunnetaan olio json-muotoon, niin jätä pois paikkaid, ettei synny ikuista luuppia
    @JoinColumn(name = "paikkaId")
	private Paikka paikka;
	
	@ManyToOne  	// Suoritus(*) - Tarjoaja(1)
    @JsonIgnore
    @JoinColumn(name = "tarjoajaId")
	private Tarjoaja tarjoaja;
	
	// konstruktorit
	
	public Suoritus() {
		super();
		this.laji = null;
		this.kulutus = 0;
		this.alkamisaika = null;
		this.loppumisaika = null;
		this.viikonpaiva = null;
		this.paikka = null;
		this.tarjoaja = null;
		
	}
	
	public Suoritus(String laji, int kulutus, String alkamisaika, String loppumisaika, Viikonpaiva viikonpaiva, Paikka paikka, Tarjoaja tarjoaja) {
		super();
		this.laji = laji;
		this.kulutus = kulutus;
		this.alkamisaika = alkamisaika;
		this.loppumisaika = loppumisaika;
		this.viikonpaiva = viikonpaiva;
		this.paikka = paikka;
		this.tarjoaja = tarjoaja;
		
	}
	
	
	// getterit ja setterit
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getLaji() {
		return laji;
	}
	public void setLaji(String laji) {
		this.laji = laji;
	}
	public int getKulutus() {
		return kulutus;
	}
	public void setKulutus(int kulutus) {
		this.kulutus = kulutus;
	}
	public String getAlkamisaika() {
		return alkamisaika;
	}
	public void setAlkamisaika(String alkamisaika) {
		this.alkamisaika = alkamisaika;
	}
	public String getLoppumisaika() {
		return loppumisaika;
	}
	public void setLoppumisaika(String loppumisaika) {
		this.loppumisaika = loppumisaika;
	}

	public Viikonpaiva getViikonpaiva() {
		return viikonpaiva;
	}
	public void setViikonpaiva(Viikonpaiva viikonpaiva) {
		this.viikonpaiva = viikonpaiva;
	}
	public Paikka getPaikka() {
		return paikka;
	}
	public void setPaikka(Paikka paikka) {
		this.paikka = paikka;
	}
	public Tarjoaja getTarjoaja() {
		return tarjoaja;
	}
	public void setTarjoaja(Tarjoaja tarjoaja) {
		this.tarjoaja = tarjoaja;
	}


	@Override
	public String toString() {
		if (this.paikka != null && this.tarjoaja != null && this.viikonpaiva != null)
			return "Suoritus [id=" + id + ", laji=" + laji + ", kulutus=" + kulutus + ", alkamisaika=" + alkamisaika
					+ ", loppumisaika=" + loppumisaika + ", viikonpaiva=" + this.getViikonpaiva() + ", paikka=" + this.getPaikka() + ", tarjoaja=" + this.getTarjoaja() + "]";			
		else
			return "Suoritus [id=" + id + ", laji=" + laji + ", kulutus=" + kulutus + ", alkamisaika=" + alkamisaika
					+ ", loppumisaika=" + loppumisaika + "]";
		
	}
	
	
}
