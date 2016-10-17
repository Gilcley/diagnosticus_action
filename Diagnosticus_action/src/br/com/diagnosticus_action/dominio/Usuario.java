package br.com.diagnosticus_action.dominio;

import javax.persistence.*;


@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	@SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "usuario_seq", allocationSize = 1)
	@Column(name = "idusuario")
	private Integer IdUsuario;

	@ManyToOne
	@JoinColumn(name = "idtipousuario")
	private TipoUsuario TipoUsuario;

	@Column(name = "nome")
	private String Nome;

	@Column(name = "matricula")
	private String Matricula;

	@Column(name = "email")
	private String Email;

	@Column(name = "senha")
	private String Senha;

	@Column(name = "apelido")
	private String Apelido;
	
	@Column(name = "imagem_usuario")
	private byte[] ImagemUsuario;
	
	@Column(name = "tipo_imagem_usuario")
	private String TipoImagemUsuario;

	public Usuario() {
	}

	public Integer getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		IdUsuario = idUsuario;
	}

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario TipoUsuario) {
		this.TipoUsuario = TipoUsuario;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getApelido() {
		return Apelido;
	}

	public void setApelido(String apelido) {
		Apelido = apelido;
	}

	public byte[] getImagemUsuario() {
		return ImagemUsuario;
	}

	public void setImagemUsuario(byte[] imagemUsuario) {
		ImagemUsuario = imagemUsuario;
	}

	public String getTipoImagemUsuario() {
		return TipoImagemUsuario;
	}

	public void setTipoImagemUsuario(String tipoImagemUsuario) {
		TipoImagemUsuario = tipoImagemUsuario;
	}	
	
	public boolean equals(Object object){
		if ((object instanceof Usuario) && ((Usuario)object).IdUsuario == this.IdUsuario)
			return true;
		
		return false;
	}

}
