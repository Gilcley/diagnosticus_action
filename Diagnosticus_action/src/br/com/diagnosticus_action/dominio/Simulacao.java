package br.com.diagnosticus_action.dominio;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "simulacao")
public class Simulacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SIMULACAO_SEQ")
	@SequenceGenerator(name = "SIMULACAO_SEQ", sequenceName = "simulacao_seq", allocationSize = 1)
	@Column(name = "idsimulacao")
	private Integer IdSimulacao;
	

	@ManyToMany
	@JoinTable(name = "simulacao_usuario", joinColumns = { @JoinColumn(name = "idsimulacao") }, inverseJoinColumns = { @JoinColumn(name = "idusuario") })
	private Set<Usuario> Usuarios = new HashSet<Usuario>();
	
	@Transient
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "som_idsom")
	private Som IdSom;
	
	@ManyToOne
	@JoinColumn(name = "professor")
	private Usuario Professor;


	@ManyToOne
	@JoinColumn(name = "idcasoemergencial")
	private CasoEmergencial IdCasoEmergencial;

	@Column(name = "descricaosimulacao")
	private String DescricaoSimulacao;

	@Column(name = "datacadastro")
	private Date DataCadastro;

	@Column(name = "temponecessario")
	private Date TempoNecessario;

	@Column(name = "tempoexamepronto")
	private Date TempoExamePronto;
	
	@Column(name = "tempo_vida_paciente")
	private Date TempoVidaPaciente;

	public Simulacao() {
	}

	public Integer getIdSimulacao() {
		return IdSimulacao;
	}

	public void setIdSimulacao(Integer idSimulacao) {
		IdSimulacao = idSimulacao;
	}

	public Som getIdSom() {
		return IdSom;
	}

	public void setIdSom(Som idSom) {
		IdSom = idSom;
	}


	public CasoEmergencial getIdCasoEmergencial() {
		return IdCasoEmergencial;
	}

	public void setIdCasoEmergencial(CasoEmergencial idCasoEmergencial) {
		IdCasoEmergencial = idCasoEmergencial;
	}

	public String getDescricaoSimulacao() {
		return DescricaoSimulacao;
	}

	public void setDescricaoSimulacao(String descricaoSimulacao) {
		DescricaoSimulacao = descricaoSimulacao;
	}

	public Date getDataCadastro() {
		return DataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		DataCadastro = dataCadastro;
	}

	public Date getTempoNecessario() {
		return TempoNecessario;
	}

	public void setTempoNecessario(Date tempoNecessario) {
		TempoNecessario = tempoNecessario;
	}

	public Date getTempoExamePronto() {
		return TempoExamePronto;
	}

	public void setTempoExamePronto(Date tempoExamePronto) {
		TempoExamePronto = tempoExamePronto;
	}

	public Date getTempoVidaPaciente() {
		return TempoVidaPaciente;
	}

	public void setTempoVidaPaciente(Date tempoVidaPaciente) {
		TempoVidaPaciente = tempoVidaPaciente;
	}

	public Usuario getProfessor() {
		return Professor;
	}

	public void setProfessor(Usuario professor) {
		Professor = professor;
	}

	public Set<Usuario> getUsuarios() {
		return Usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		Usuarios = usuarios;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public String getTempoNecessarioFormatado(){
		return (new SimpleDateFormat("HH:mm").format(TempoNecessario));
	}
	
}
