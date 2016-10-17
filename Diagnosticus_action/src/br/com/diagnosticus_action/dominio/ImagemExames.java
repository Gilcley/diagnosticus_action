package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

@Entity
@Table(name = "imagem_exames")
public class ImagemExames {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGEM_EXAMES_SEQ")
	@SequenceGenerator(name = "IMAGEM_EXAMES_SEQ", sequenceName = "imagem_exames_seq", allocationSize = 1)
	@Column(name = "idimagem_exames")
	private Integer IdImagemExames;

	public ImagemExames(){}
	
	@Column(name = "imagem")
	private byte[] Imagem;
	
	@Column(name = "tipo_imagem")
	private String TipoImagem;

	public Integer getIdImagemExames() {
		return IdImagemExames;
	}

	public void setIdImagemExames(Integer idImagemExames) {
		IdImagemExames = idImagemExames;
	}

	public String getTipoImagem() {
		return TipoImagem;
	}

	public void setTipoImagem(String tipoImagem) {
		TipoImagem = tipoImagem;
	}

	public byte[] getImagem() {
		return Imagem;
	}

	public void setImagem(byte[] imagem) {
		Imagem = imagem;
	}
	
	
	
	


}
