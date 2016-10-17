package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "condutamedica")
public class CondutaMedica {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONDUTA_MEDICA_SEQ")
	@SequenceGenerator(name = "CONDUTA_MEDICA_SEQ", sequenceName = "conduta_medica_seq", allocationSize = 1)
	@Column(name = "idcondutamedica")
	private Integer IdCondutaMedica;

	@ManyToOne
	@JoinColumn(name = "idsimulacao")
	private Simulacao Simulacao;

	@Column(name = "condutamedica")
	private String condutaMedica;

	public CondutaMedica() {

	}

	public Integer getIdCondutaMedica() {
		return IdCondutaMedica;
	}

	public void setIdCondutaMedica(Integer idCondutaMedica) {
		IdCondutaMedica = idCondutaMedica;
	}

	public Simulacao getSimulacao() {
		return Simulacao;
	}

	public void setSimulacao(Simulacao simulacao) {
		Simulacao = simulacao;
	}

	public String getCondutaMedica() {
		return condutaMedica;
	}

	public void setCondutaMedica(String condutaMedica) {
		this.condutaMedica = condutaMedica;
	}

}
