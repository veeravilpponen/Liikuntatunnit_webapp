package vv.projekti.suoritusApp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Paikka {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long paikkaId;
	
	@NotEmpty
	private String nimi;	//liikuntapaikan nimi
	
	// Paikka(1) - Suoritus(*)
	// cascade, jos poistat kategorian, niin kaikki sen paikan urheilulajit katoaa kannasta
	// mappedby, viittaa toisen, Urheilulaji-taulun, attributtin nimeen "private Category category;"
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paikka")
	// lista paikan urheilulajeista
	private List<Suoritus> suoritukset;

	// konstruktorit
	
	public Paikka() {
		super();
		this.nimi = null;
	}
	
	public Paikka(String nimi) {
		super();
		this.nimi = nimi;
	}
	
	//getterit ja setterit

	public Long getPaikkaId() {
		return paikkaId;
	}

	public void setPaikkaId(Long paikkaId) {
		this.paikkaId = paikkaId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public List<Suoritus> getSuoritukset() {
		return suoritukset;
	}

	public void setSuoritukset(List<Suoritus> suoritukset) {
		this.suoritukset = suoritukset;
	}

	@Override
	public String toString() {
		return "Paikka [paikkaId=" + paikkaId + ", nimi=" + nimi + "]";
	}
	
}
