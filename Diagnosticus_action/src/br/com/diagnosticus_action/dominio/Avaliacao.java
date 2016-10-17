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
@Table(name = "avalicao")
public class Avaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AVALIACAO_SEQ")
	@SequenceGenerator(name = "AVALIACAO_SEQ", sequenceName = "avaliacao_seq", allocationSize = 1)
	@Column(name = "idavalicao")
	private Integer IdAvaliacao;

	@ManyToOne
	@JoinColumn(name = "idjogo")
	private Jogo Jogo;

	@Column(name = "observacao")
	private String Observacao;

	@Column(name = "nota")
	private Float Nota;

	public Avaliacao() {

	}

	public Integer getIdAvaliacao() {
		return IdAvaliacao;
	}

	public void setIdAvaliacao(Integer idAvaliacao) {
		IdAvaliacao = idAvaliacao;
	}

	public Jogo getJogo() {
		return Jogo;
	}

	public void setJogo(Jogo jogo) {
		Jogo = jogo;
	}

	public String getObservacao() {
		return Observacao;
	}

	public void setObservacao(String observacao) {
		Observacao = observacao;
	}

	public Float getNota() {
		return Nota;
	}

	public void setNota(Float nota) {
		Nota = nota;
	}
	
	

}
