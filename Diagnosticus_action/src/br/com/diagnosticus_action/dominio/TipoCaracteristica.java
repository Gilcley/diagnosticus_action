package br.com.diagnosticus_action.dominio;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "tipocaracteristica")
public class TipoCaracteristica implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_CARACTERISTICA_SEQ")
	@SequenceGenerator(name = "TIPO_CARACTERISTICA_SEQ", sequenceName = "tipo_caracteristica_seq", allocationSize = 1)
	@Column(name = "idtipocaracteristica")
	private Integer IdTipoCaracteristica;

	@Column(name = "descricaotipocaracteristica")
	private String DescricaoTipoCaracteristica;

	public TipoCaracteristica() {
	}

	public Integer getIdTipoCaracteristica() {
		return IdTipoCaracteristica;
	}

	public void setIdTipoCaracteristica(Integer idTipoCaracteristica) {
		IdTipoCaracteristica = idTipoCaracteristica;
	}

	public String getDescricaoTipoCaracteristica() {
		return DescricaoTipoCaracteristica;
	}

	public void setDescricaoTipoCaracteristica(
			String descricaoTipoCaracteristica) {
		DescricaoTipoCaracteristica = descricaoTipoCaracteristica;
	}

}
