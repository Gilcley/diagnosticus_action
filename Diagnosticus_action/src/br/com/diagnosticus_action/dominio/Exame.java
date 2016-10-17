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
@Table(name = "exame")
public class Exame {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXAME_SEQ")
	@SequenceGenerator(name = "EXAME_SEQ", sequenceName = "exame_seq", allocationSize = 1)
	@Column(name = "idexame")
	private Integer IdExame;

	@ManyToOne
	@JoinColumn(name = "tipoexame_idtipoexame")
	private TipoExame IdTipoExame;

	@ManyToOne
	@JoinColumn(name = "idimagem_exames")
	private ImagemExames IdImagem;

	@Column(name = "nomeexame")
	private String NomeExame;

	@Column(name = "raso")
	private String Raso;

	public Exame() {

	}

	public Integer getIdExame() {
		return IdExame;
	}
	

	public void setIdExame(Integer idExame) {
		IdExame = idExame;
	}

	public TipoExame getIdTipoExame() {
		return IdTipoExame;
	}

	public void setIdTipoExame(TipoExame idTipoExame) {
		IdTipoExame = idTipoExame;
	}

	public ImagemExames getIdImagem() {
		return IdImagem;
	}

	public void setIdImagem(ImagemExames idImagem) {
		IdImagem = idImagem;
	}

	public String getNomeExame() {
		return NomeExame;
	}

	public void setNomeExame(String nomeExame) {
		NomeExame = nomeExame;
	}

	public String getRaso() {
		return Raso;
	}

	public void setRaso(String raso) {
		Raso = raso;
	}

}
