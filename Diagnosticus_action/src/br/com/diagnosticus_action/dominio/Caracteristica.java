package br.com.diagnosticus_action.dominio;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "caracteristica")
public class Caracteristica implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARACTERISTICA_SEQ")
	@SequenceGenerator(name = "CARACTERISTICA_SEQ", sequenceName = "caracteristica_seq", allocationSize = 1)
	@Column(name = "id_caracteristica")
	private Integer IdCaracteristica;

	@Column(name = "descricaocaracteristica")
	private String DescricaoCaracteristica;

	@ManyToOne
	@JoinColumn(name = "idtipocaracteristica")
	private TipoCaracteristica tipoCaracteristica;

	public Caracteristica() {
	}

	public Integer getIdCaracteristica() {
		return IdCaracteristica;
	}

	public void setIdCaracteristica(Integer idCaracteristica) {
		IdCaracteristica = idCaracteristica;
	}

	public String getDescricaoCaracteristica() {
		return DescricaoCaracteristica;
	}

	public void setDescricaoCaracteristica(String descricaoCaracteristica) {
		DescricaoCaracteristica = descricaoCaracteristica;
	}

	public TipoCaracteristica getTipoCaracteristica() {
		return tipoCaracteristica;
	}

	public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristica) {
		this.tipoCaracteristica = tipoCaracteristica;
	}

}
