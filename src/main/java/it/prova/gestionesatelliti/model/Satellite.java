package it.prova.gestionesatelliti.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionesatelliti.validation.constraints.DateCheck;

@Entity
@Table(name = "satellite")
@DateCheck
public class Satellite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "{denominazione.notblank}")
	@Column(name = "denominazione")
	private String denominazione;

	@NotBlank(message = "{codice.notblank}")
	@Column(name = "codice")
	private String codice;

	@Column(name = "dataLancio")
	private Date dataLancio;
	
	@Column(name = "dataRientro")
	private Date dataRientro;

	@Column(name = "statoSatellite")
	@Enumerated(EnumType.STRING)
	private StatoSatellite statoSatellite;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Date getDataLancio() {
		return dataLancio;
	}

	public void setDataLancio(Date dataLancio) {
		this.dataLancio = dataLancio;
	}

	public Date getDataRientro() {
		return dataRientro;
	}

	public void setDataRientro(Date dataRientro) {
		this.dataRientro = dataRientro;
	}

	public StatoSatellite getStatoSatellite() {
		return statoSatellite;
	}

	public void setStatoSatellite(StatoSatellite statoSatellite) {
		this.statoSatellite = statoSatellite;
	}

}
