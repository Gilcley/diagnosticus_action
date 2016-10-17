package br.com.diagnosticus_action.dominio;

import java.util.Date;
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
@Table(name = "jogo")
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOGO_SEQ")
	@SequenceGenerator(name = "JOGO_SEQ", sequenceName = "jogo_seq", allocationSize = 1)
	@Column(name = "idjogo")
	private Integer IdJogo;

	@ManyToOne
	@JoinColumn(name = "idcondutamedica")
	private CondutaMedica CondutaMedica;
	
	@ManyToOne
	@JoinColumn(name = "aluno")
	private Usuario Aluno;

	@ManyToMany
	@JoinTable(name = "exame_jogo", joinColumns = { @JoinColumn(name = "idjogo") }, inverseJoinColumns = { @JoinColumn(name = "idexame") })
	private Set<Exame> Exame = new HashSet<Exame>();
	
	@ManyToOne
	@JoinColumn(name = "iddiagnostico")
	private Diagnostico Diagnostico;
	
	@ManyToOne
	@JoinColumn(name = "idsimulacao")
	private Simulacao Simulacao;

	

	@Column(name = "datahorainicio")
	private Date DataHoraInicio;
	
	@Column(name = "datahorafim")
	private Date DataHoraFim;
	
	@Column(name = "hora_solicitacao_exame")
	private String HoraSolicitacaoExame;
	
	@Column(name = "istimeout")
	private boolean IsTimeOut;
	
	@Column(name = "is_paciente_morreu")
	private boolean PacienteMorreu;

	public Jogo() {

	}

	public Integer getIdJogo() {
		return IdJogo;
	}

	public void setIdJogo(Integer idJogo) {
		IdJogo = idJogo;
	}

	public CondutaMedica getCondutaMedica() {
		return CondutaMedica;
	}

	public void setCondutaMedica(CondutaMedica condutaMedica) {
		CondutaMedica = condutaMedica;
	}

	public Diagnostico getDiagnostico() {
		return Diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		Diagnostico = diagnostico;
	}

	public Simulacao getSimulacao() {
		return Simulacao;
	}

	public void setSimulacao(Simulacao simulacao) {
		Simulacao = simulacao;
	}

	public Date getDataHoraInicio() {
		return DataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		DataHoraInicio = dataHoraInicio;
	}

	public Date getDataHoraFim() {
		return DataHoraFim;
	}

	public void setDataHoraFim(Date dataHoraFim) {
		DataHoraFim = dataHoraFim;
	}

	public boolean isIsTimeOut() {
		return IsTimeOut;
	}

	public void setIsTimeOut(boolean isTimeOut) {
		IsTimeOut = isTimeOut;
	}

	public Set<Exame> getExame() {
		return Exame;
	}

	public void setExame(Set<Exame> exame) {
		Exame = exame;
	}

	public boolean isPacienteMorreu() {
		return PacienteMorreu;
	}

	public void setPacienteMorreu(boolean pacienteMorreu) {
		PacienteMorreu = pacienteMorreu;
	}

	public Usuario getAluno() {
		return Aluno;
	}

	public void setAluno(Usuario aluno) {
		Aluno = aluno;
	}

	public String getHoraSolicitacaoExame() {
		return HoraSolicitacaoExame;
	}

	public void setHoraSolicitacaoExame(String horaSolicitacaoExame) {
		HoraSolicitacaoExame = horaSolicitacaoExame;
	}
		
	
	
	

}
