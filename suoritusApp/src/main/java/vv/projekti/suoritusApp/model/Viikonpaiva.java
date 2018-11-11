package vv.projekti.suoritusApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Viikonpaiva {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long viikonpaivaId;
	
	private String nimi;	//liikuntapalvelun tarjojan nimi

	// Viikonpaiva(1) - Suoritus(*)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "viikonpaiva")
	// lista tietyn paivan urheilulajeista
	private List<Suoritus> suoritukset;

	// konstruktorit
	
	public Viikonpaiva() {
		super();
		this.nimi = null;
	}
	
	public Viikonpaiva(String nimi) {
		super();
		this.nimi = nimi;
	}

	// getterit ja setterit

	public Long getViikonpaivaId() {
		return viikonpaivaId;
	}

	public void setViikonpaivaId(Long viikonpaivaId) {
		this.viikonpaivaId = viikonpaivaId;
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
		return "Viikonpaiva [viikonpaivaId=" + viikonpaivaId + ", nimi=" + nimi +  "]";
	}
	

}