package br.com.diagnosticus_action.dominio;

import java.util.Date;
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
@Table (name="paciente")
public class Paciente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PACIENTE_SEQ")
    @SequenceGenerator(name = "PACIENTE_SEQ", sequenceName = "paciente_seq", allocationSize = 1)
    @Column(name = "idpaciente")
	private Integer IdPaciente;
	
	@ManyToOne
	@JoinColumn(name="idnaturalidade")
	private Naturalidade Naturalidade;
	
	
	@ManyToOne
	@JoinColumn(name="idprofissao")
	private Profissao Profissao;
	
	@ManyToOne
	@JoinColumn(name="idraca")
	private Raca Raca;
	
	@ManyToOne
	@JoinColumn(name="idestadocivil")
	private EstadoCivil EstadoCivil;
	
	@ManyToOne
	@JoinColumn(name="idgenero")
	private Genero Genero;
	
	@Column(name = "nome")
	private String Nome;
	
	@Column(name = "datanascimento")
	private Date DataNascimento;
	
	@Column(name = "hda")
	private String Hda;
	
	@Column(name = "antecedentespessoais")
	private String AntecedentesPessoais;
	
	@Column(name = "antecedentesfamiliares")
	private String AntecedentesFamiliares;
	
	@Column(name = "historicopsicossocialhabitosvicios")
	private String HistoricoPsicosSocialHabitosVicios;
	
	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Integer getIdPaciente() {
		return IdPaciente;
	}

	public void setIdPaciente(Integer pIdPaciente) {
		IdPaciente = pIdPaciente;
	}

	public Naturalidade getNaturalidade() {
		return Naturalidade;
	}

	public void setNaturalidade(Naturalidade naturalidade) {
		Naturalidade = naturalidade;
	}

	public Profissao getProfissao() {
		return Profissao;
	}

	public void setProfissao(Profissao profissao) {
		Profissao = profissao;
	}

	public Raca getRaca() {
		return Raca;
	}

	public void setRaca(Raca idRaca) {
		Raca = idRaca;
	}

	public EstadoCivil getEstadoCivil() {
		return EstadoCivil;
	}

	public void setEstadoCivil(EstadoCivil idEstadoCivil) {
		EstadoCivil = idEstadoCivil;
	}

	public Genero getGenero() {
		return Genero;
	}

	public void setGenero(Genero idGenero) {
		Genero = idGenero;
	}

	
	public String getNome() {
		return Nome;
	}

	public void setNome(String pNome) {
		Nome = pNome;
	}

	public Date getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(Date pDataNascimento) {
		DataNascimento = pDataNascimento;
	}

	public String getAntecedentesPessoais() {
		return AntecedentesPessoais;
	}

	public void setAntecedentesPessoais(String pAntecedentesPessoais) {
		AntecedentesPessoais = pAntecedentesPessoais;
	}

	public String getAntecedentesFamiliares() {
		return AntecedentesFamiliares;
	}

	public void setAntecedentesFamiliares(String pAntecedentesFamiliares) {
		AntecedentesFamiliares = pAntecedentesFamiliares;
	}

	public String getHistoricoPsicossocialHabitosVicios() {
		return HistoricoPsicosSocialHabitosVicios;
	}

	public void setHistoricoPsicossocialHabitosVicios(
			String pHistoricoPsicossocialHabitosVicios) {
		HistoricoPsicosSocialHabitosVicios = pHistoricoPsicossocialHabitosVicios;
	}


	public String getHda() {
		return Hda;
	}


	public void setHda(String hda) {
		Hda = hda;
	}
	
	
}
