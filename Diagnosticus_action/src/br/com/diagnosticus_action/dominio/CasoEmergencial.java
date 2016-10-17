package br.com.diagnosticus_action.dominio;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "casoemergencial")
public class CasoEmergencial {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CASOEMERGENCIAL_SEQ")
	@SequenceGenerator(name = "CASOEMERGENCIAL_SEQ", sequenceName = "casoemergencial_seq", allocationSize = 1)
	@Column(name = "idcasoemergencial")
	private Integer IdCasoEmergencial;

	@ManyToOne
	@JoinColumn(name = "idpaciente")
	private Paciente Paciente;
	
	@ManyToOne
	@JoinColumn(name = "idqueixa")
	private Queixa QueixaPrincipal;
	
	@ManyToOne
	@JoinColumn(name = "codigo_cid")
	private CID cid;

	@Column(name = "descricaocaso")
	private String DescricaoCaso;

	@ManyToMany
	@JoinTable(name = "casoemergencial_exame", joinColumns = { @JoinColumn(name = "idcasoemergencial") }, inverseJoinColumns = { @JoinColumn(name = "idexame") })
	private Set<Exame> Exame = new HashSet<Exame>();
	
	@ManyToMany
	@JoinTable(name = "queixa_casoemergencial", joinColumns = { @JoinColumn(name = "idcasoemergencial") }, inverseJoinColumns = { @JoinColumn(name = "idqueixa") })
	private Set<Queixa> Queixa = new HashSet<Queixa>();

	@Column(name = "dadoexamefisico")
	private String DadosExameFisico;

	public CasoEmergencial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDadosExameFisico() {
		return DadosExameFisico;
	}

	public void setDadosExameFisico(String dadosExameFisico) {
		DadosExameFisico = dadosExameFisico;
	}

	public Integer getIdCasoEmergencial() {
		return IdCasoEmergencial;
	}

	public Paciente getPaciente() {
		return Paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.Paciente = paciente;
	}

	public String getDescricaoCaso() {
		return DescricaoCaso;
	}

	public void setDescricaoCaso(String pDescricaoCaso) {
		DescricaoCaso = pDescricaoCaso;
	}

	public Set<Exame> getExame() {
		return Exame;
	}

	public void setExame(Set<Exame> exame) {
		Exame = exame;
	}

	public void setIdCasoEmergencial(Integer idCasoEmergencial) {
		IdCasoEmergencial = idCasoEmergencial;
	}

	public void setQueixa(Set<Queixa> queixa) {
		Queixa = queixa;
	}

	public Set<Queixa> getQueixa() {
		return Queixa;
	}

	public Queixa getQueixaPrincipal() {
		return QueixaPrincipal;
	}

	public void setQueixaPrincipal(Queixa queixaPrincipal) {
		QueixaPrincipal = queixaPrincipal;
	}

	public CID getCid() {
		return cid;
	}

	public void setCid(CID cid) {
		this.cid = cid;
	}
}
