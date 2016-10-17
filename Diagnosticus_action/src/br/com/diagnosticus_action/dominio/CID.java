package br.com.diagnosticus_action.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cid" )
public class CID {
		
	@Id
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "classificacao")
	private String classificacao;
	
	@Column(name = "restr_sexo")
	private String restrSexo;
	
	@Column(name = "causa_obito")
	private String causaObito;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "descricao_abreviada")
	private String descricaoAbreviada;
	
	@Column(name = "referencia")
	private String referencia;
	
	@Column(name = "excluidos")
	private String excluidos;

	public CID (){}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getRestrSexo() {
		return restrSexo;
	}

	public void setRestrSexo(String restrSexo) {
		this.restrSexo = restrSexo;
	}

	public String getCausaObito() {
		return causaObito;
	}

	public void setCausaObito(String causaObito) {
		this.causaObito = causaObito;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getExcluidos() {
		return excluidos;
	}

	public void setExcluidos(String excluidos) {
		this.excluidos = excluidos;
	}
	
}
