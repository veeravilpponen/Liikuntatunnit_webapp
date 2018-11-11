package vv.projekti.suoritusApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tarjoaja {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long tarjoajaId;
	
	private String nimi;	//liikuntapalvelun tarjojan nimi

	// Tarjoaja(1) - Urheilulaji(*)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tarjoaja")
	// lista tarjoajan urheilulajeista
	private List<Suoritus> suoritukset;

	// konstruktorit
	
	public Tarjoaja() {
		super();
		this.nimi = null;
	}
	
	public Tarjoaja(String nimi) {
		super();
		this.nimi = nimi;
	}

	// getterit ja setterit
	
	public Long getTarjoajaId() {
		return tarjoajaId;
	}

	public void setTarjoajaId(Long tarjoajaId) {
		this.tarjoajaId = tarjoajaId;
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
		return "Tarjoaja [tarjoajaId=" + tarjoajaId + ", nimi=" + nimi + "]";
	}

}