package br.com.diagnosticus_action.Tratador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import br.com.diagnosticus_action.Cadastro.*;
import br.com.diagnosticus_action.dominio.CasoEmergencial;
import br.com.diagnosticus_action.dominio.Exame;
import br.com.diagnosticus_action.dominio.Jogo;
import br.com.diagnosticus_action.dominio.Queixa;
import br.com.diagnosticus_action.dominio.Simulacao;
import br.com.diagnosticus_action.dominio.Turma;
import br.com.diagnosticus_action.dominio.Usuario;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorSimulacao")
@SessionScoped
public class TratadorDeSimulacao {
	private Simulacao simulacao = new Simulacao();
	private Usuario usuario = new Usuario();
	private CasoEmergencial casoemergencial= new CasoEmergencial();
	
	private CadastroCasosEmergenciais cadastroCasoEmergencial;
	private CadastroJogo cadastrojogo = new CadastroJogo();
	private CadastroUsuario cadastroUsuario; 
	private CadastroSimulacao cadastroSimulacao;
	private CadastroTurma cadastroTurma;
	private List<SelectItem> selectSimulacao;
	private List<Simulacao> listaSimulacoes = new ArrayList<>();
	private List<Usuario> listaAlunosSimulacao = new ArrayList<>();
	
	/** Armazena a turma para a qual a simulação foi atribuída*/
	private Turma turma = new Turma(); 
	
	/** Informa se o tipo de seleção será por turma ou por aluno*/
	private boolean porTurma = true;

	public TratadorDeSimulacao() {

	}

	public void starSession() {
		this.cadastrojogo = DAOFactory.criarJogoDao();
		this.cadastroSimulacao = DAOFactory.criarSimulacaoDAO();
		this.cadastroCasoEmergencial = DAOFactory.criarCasoEmergencialDAO();
		this.cadastroUsuario = DAOFactory.criarUsuarioDAO();
		this.cadastroTurma = DAOFactory.criarTurmaDAO();
	}
	
	public String limpaCampos(){
		listaSimulacoes  = new ArrayList<>();
		listaAlunosSimulacao = new ArrayList<>();
		simulacao = new Simulacao();
		casoemergencial = new CasoEmergencial();
		usuario = new Usuario();
		starSession();
		return null;
	}

	public String adicionarSimulacao() {
		this.cadastroSimulacao = new CadastroSimulacao();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		
		if(porTurma)
			carregarTurma();
		
		if(!validaCampos()){
			return null;
		}
		
		Usuario usuariologado= (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get("usuariosimulacoes");
		try {
//			cadastroUsuario.carregar(this.usuario.getIdUsuario());
			simulacao.setIdCasoEmergencial(cadastroCasoEmergencial.carregar(casoemergencial.getIdCasoEmergencial()));
			simulacao.setProfessor(usuariologado);
			
			Set<Usuario> alunos = new HashSet<Usuario>();
			for (int i = 0; i < listaAlunosSimulacao.size(); i++) {
				alunos.add(listaAlunosSimulacao.get(i));
			}
			simulacao.setUsuarios(alunos);
			
			Date data = new Date();
			simulacao.setDataCadastro(data);
			this.cadastroSimulacao.salvar(simulacao);
			facesMessage = new FacesMessage("Inserida com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir!");
		} finally {
			context.addMessage(null, facesMessage);
		}
		limpaCampos();
		return null;

	}
	
	/** Carrega a lista de alunos com todos os alunos referentes aquela turma*/
	private void carregarTurma(){
		starSession();
		turma = cadastroTurma.carregar(turma.getId());
		listaAlunosSimulacao.addAll(turma.getAlunos());
	}
	
	public boolean validaCampos(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		if(simulacao.getDescricaoSimulacao().isEmpty()){
			facesMessage = new FacesMessage("O campo descrição não pode ser vazio");
			context.addMessage(null, facesMessage);
			return false;
		}
		if(listaAlunosSimulacao.size()==0){
			facesMessage = new FacesMessage("Escolha pelo menos um aluno.");
			context.addMessage(null, facesMessage);
			return false;
		}
		if(simulacao.getIdCasoEmergencial()==null && casoemergencial.getIdCasoEmergencial()==null){
			facesMessage = new FacesMessage("Escolha um caso emergencial");
			context.addMessage(null, facesMessage);
			return false;
		}
		if(simulacao.getTempoExamePronto()==null){
			facesMessage = new FacesMessage("O campo tempo do exame pronto não pode ser vazio.");
			context.addMessage(null, facesMessage);
			return false;
		}
		if(simulacao.getTempoNecessario()==null){
			facesMessage = new FacesMessage("O campo tempo da simulação não pode ser vazio.");
			context.addMessage(null, facesMessage);
			return false;
		}
		if(simulacao.getTempoVidaPaciente() ==null){
			facesMessage = new FacesMessage("O campo tempo de vida do paciente não pode ser vazio.");
			context.addMessage(null, facesMessage);
			return false;
		}
		return true;
	}
	
	public String alterarSimulacao() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		if(!validaCampos()){
			return null;
		}
		List<Jogo> jogo = cadastrojogo.buscarJogo(simulacao.getIdSimulacao());
		
		if(jogo.size()>0){
			facesMessage = new FacesMessage("Algum Aluno já jogou esta simlação, não é possível alterá-la.");
			context.addMessage(null, facesMessage);
			return null;
			
		}
		try {

			Simulacao novasimulacao = cadastroSimulacao.carregar(simulacao.getIdSimulacao());
			
			novasimulacao.setIdCasoEmergencial(cadastroCasoEmergencial.carregar(simulacao.getIdCasoEmergencial().getIdCasoEmergencial()));
			novasimulacao.setUsuario(cadastroUsuario.carregar(simulacao.getUsuario().getIdUsuario()));
			
			Set<Usuario> alunos = new HashSet<Usuario>();
			for (int i = 0; i < listaAlunosSimulacao.size(); i++) {
				alunos.add(listaAlunosSimulacao.get(i));
			}
			novasimulacao.setUsuarios(alunos);
			
			novasimulacao.setDescricaoSimulacao(simulacao.getDescricaoSimulacao());
			novasimulacao.setTempoExamePronto(simulacao.getTempoExamePronto());
			novasimulacao.setTempoNecessario(simulacao.getTempoNecessario());
			novasimulacao.setTempoVidaPaciente(simulacao.getTempoVidaPaciente());
			
			
			
			
			cadastroSimulacao.atualizar(novasimulacao);
			facesMessage = new FacesMessage(" A Simulação foi alterada com sucesso!");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível alterar a Simulação!");
		} finally {
			limpaCampos();
			context.addMessage(null, facesMessage);
		}
		return null;

	}


	public List<Simulacao> listar() throws SQLException {
		this.cadastroSimulacao = new CadastroSimulacao();
		starSession();
//		cadastroSimulacao.consultarSimulacaoPorAluno(idUsuario);
		listaSimulacoes = new ArrayList<>();
		Usuario usuariologado= (Usuario) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get("usuariosimulacoes");
		List<Simulacao>  todasSimulacoes= this.cadastroSimulacao.listar();
		for (Simulacao simAux : todasSimulacoes ) {
			if(simAux.getProfessor().getIdUsuario()== usuariologado.getIdUsuario()){
				listaSimulacoes.add(simAux);
			}
		}
		return listaSimulacoes;
	}

	public List<SelectItem> getSelectSimulacao() {
		this.cadastroSimulacao = new CadastroSimulacao();
		starSession();
		List<Simulacao> listaSimulacao = this.cadastroSimulacao.listar();
		this.selectSimulacao = new ArrayList<>();
		for (Simulacao simulacao : listaSimulacao) {
			this.selectSimulacao.add(new SelectItem(simulacao, simulacao.getDescricaoSimulacao()));
		}
		return selectSimulacao;
	}
	
	public String consultarSimulacao(){
		listaAlunosSimulacao = new ArrayList<>();
		this.simulacao = cadastroSimulacao.carregar(simulacao.getIdSimulacao());
		for (Usuario aluno : simulacao.getUsuarios()) {
			listaAlunosSimulacao.add(aluno);
		} 
		return null;
	}
	
	public String formataTempoExame(){
		return formataTempo(simulacao.getTempoExamePronto());
	}
	
	public String formataTempoNecessario(){
		return formataTempo(simulacao.getTempoNecessario());
	}
	
	public String formataTempoVidaPaciente(){
		return formataTempo(simulacao.getTempoVidaPaciente());
	}
	
	
	public String formataTempo(Date tempo){
		String formata= null;
		if(tempo.getHours()<10)
			formata = "0"+ tempo.getHours();
		if(tempo.getHours()>=10){
			formata =""+tempo.getHours();
		}
		if(tempo.getMinutes()<10){
			formata = formata+":0"+tempo.getMinutes();
			return formata;
		}
		formata = formata +":"+ tempo.getMinutes();
		return formata;
	}
	
	
   public void adicionarAluno(SelectEvent event) {
	   starSession();
	   if(usuario != null && usuario.getIdUsuario() > 0 && !listaAlunosSimulacao.contains(getUsuario()))
		   listaAlunosSimulacao.add(getUsuario());
	   
    }
	
	public String removerAluno(Usuario aluno){
		listaAlunosSimulacao.remove(aluno);
		return null;
	}

	public Simulacao getSimulacao() {
		return simulacao;
	}

	public void setSimulacao(Simulacao simulacao) {
		this.simulacao = simulacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CasoEmergencial getCasoemergencial() {
		return casoemergencial;
	}

	public void setCasoemergencial(CasoEmergencial casoemergencial) {
		this.casoemergencial = casoemergencial;
	}

	public List<Simulacao> getListaSimulacoes() {
		return listaSimulacoes;
	}

	public void setListaSimulacoes(List<Simulacao> listaSimulacoes) {
		this.listaSimulacoes = listaSimulacoes;
	}

	public List<Usuario> getListaAlunosSimulacao() {
		return listaAlunosSimulacao;
	}

	public void setListaAlunosSimulacao(List<Usuario> listaAlunosSimulacao) {
		this.listaAlunosSimulacao = listaAlunosSimulacao;
	}

	public boolean isPorTurma() {
		return porTurma;
	}

	public void setPorTurma(boolean porTurma) {
		this.porTurma = porTurma;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

}
