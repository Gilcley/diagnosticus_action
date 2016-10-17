package br.com.diagnosticus_action.Tratador;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.diagnosticus_action.Cadastro.CadastroCaracteristica;
import br.com.diagnosticus_action.Cadastro.CadastroCasosEmergenciais;
import br.com.diagnosticus_action.Cadastro.CadastroCondutaMedica;
import br.com.diagnosticus_action.Cadastro.CadastroDiagnostico;
import br.com.diagnosticus_action.Cadastro.CadastroExame;
import br.com.diagnosticus_action.Cadastro.CadastroImagemExame;
import br.com.diagnosticus_action.Cadastro.CadastroJogo;
import br.com.diagnosticus_action.Cadastro.CadastroPaciente;
import br.com.diagnosticus_action.Cadastro.CadastroSimulacao;
import br.com.diagnosticus_action.dominio.Caracteristica;
import br.com.diagnosticus_action.dominio.CasoEmergencial;
import br.com.diagnosticus_action.dominio.CondutaMedica;
import br.com.diagnosticus_action.dominio.Diagnostico;
import br.com.diagnosticus_action.dominio.Exame;
import br.com.diagnosticus_action.dominio.ImagemExames;
import br.com.diagnosticus_action.dominio.Jogo;
import br.com.diagnosticus_action.dominio.Paciente;
import br.com.diagnosticus_action.dominio.Queixa;
import br.com.diagnosticus_action.dominio.Simulacao;
import br.com.diagnosticus_action.dominio.Usuario;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorJogo")
@SessionScoped
public class TratadorDeJogo {
	private Simulacao simulacao = new Simulacao();

	private Jogo jogo = new Jogo();

	private CasoEmergencial caso = new CasoEmergencial();
	
	private Exame exame = new Exame();
	
	private Paciente paciente = new Paciente();
	
	private Diagnostico diagnostico = new Diagnostico();

	private ImagemExames imagemexames = new ImagemExames(); 

	private CondutaMedica condutaMedica = new CondutaMedica();
	
	private CadastroExame cadastroExame = new CadastroExame();
	
	private List<Exame> listaExamesSolicitados = new ArrayList<>();
	
	private List<Exame> listaExamesSolicitadosValidos = new ArrayList<>();
	
	private List<Exame> listaExamesVisualizacaoDisponivel = new ArrayList<>();

	private List<Queixa> listaQueixasPaciente = new ArrayList<>();
	
	private StreamedContent image;
	
	private int tipousuario ;
	
	
	/*Variaveis  relacionadas a logica do tempo 
	 * */
	private int tempoJogo=0;
	private int tempoAtual;
	private int tempoCoracoes=0;
	private int tempoCoracoesEstagio;
	private int tempoExameAtual=0;
	private int tempoExame = 0 ;
	private Exame exameAtual;

	private List<Caracteristica> listacaracteristica = new ArrayList<>();
	
	private CadastroJogo cadastroJogo = new CadastroJogo();
	private CadastroCondutaMedica cadastroCondutaMedica = new CadastroCondutaMedica();
	private CadastroPaciente cadastroPaciente = new CadastroPaciente();
	private CadastroCasosEmergenciais Cadastrocaso = new CadastroCasosEmergenciais();
	private CadastroDiagnostico cadastroDiagnostico = new CadastroDiagnostico();
	private CadastroSimulacao cadastroSimulacao = new CadastroSimulacao();
	private CadastroCaracteristica cadastroCaracteristica = new CadastroCaracteristica();
	private CadastroImagemExame cadastroImagemExame = new  CadastroImagemExame();

	/*Inicia a sessão para manipulação de dados com o banco*/
	public void starSession() {
		this.cadastroJogo= DAOFactory.criarJogoDao();
		this.cadastroSimulacao = DAOFactory.criarSimulacaoDAO();
		this.cadastroExame = DAOFactory.criarExameDAO();
		this.cadastroPaciente = DAOFactory.criarPacienteDAO();
		this.Cadastrocaso = DAOFactory.criarCasoEmergencialDAO();
		this.cadastroCaracteristica = DAOFactory.criarCaracteristicaDAO();
		this.cadastroDiagnostico = DAOFactory.criarDiagnosticoDAO();
		this.cadastroCondutaMedica = DAOFactory.criarCondutaMedicaDao();
		this.cadastroImagemExame = DAOFactory.criarImagemExameDAO();
	}

	public String iniciarJogo(){
		starSession();
		
		simulacao = (Simulacao) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("simulacao");
		
		caso = Cadastrocaso.carregar(simulacao.getIdCasoEmergencial().getIdCasoEmergencial());
		
		return "diagnosticus_acoes_queixas.xhtml?faces-redirect=true";
	}
	
	/**
	 * carrega as informações de um exame selecionado  e redireciona para a tela de confirmação de solicitação.
	 * */
	public String preSelecionarExame(){
		for (Exame exameAux : listaExamesSolicitados) {
			if(exameAux.getIdExame()==exame.getIdExame()){
				mensagem("Esse exame já foi solicitado. Por Favor Aguarde!", 1);
				return null;
			}
		}
		
		this.cadastroExame = DAOFactory.criarExameDAO();
		exame = cadastroExame.carregar(exame.getIdExame());
		
		return "diagnosticus_acoes_exames_complementares.xhtml";
	}
	
	
	public String CarregarExame() {
		starSession();
		caso = new CasoEmergencial();
		Simulacao teste = (Simulacao) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get("simulacao");
		jogo.setDataHoraInicio((Date) FacesContext.getCurrentInstance()
				.getExternalContext().getApplicationMap().get("horainicio"));
		
		if(tempoJogo == 0){
			tempoJogo = (teste.getTempoNecessario().getHours() * 60*60) + teste.getTempoNecessario().getMinutes()*60; 
			tempoExame = teste.getTempoExamePronto().getHours() * 3600 + teste.getTempoExamePronto().getMinutes()*60;
		}

		if(tempoCoracoes == 0){
			tempoAtual =0;
			tempoCoracoesEstagio= 0;
			float aux = (teste.getTempoVidaPaciente().getHours()*3600 + teste.getTempoVidaPaciente().getMinutes()* 60)/5;
			tempoCoracoes = (int) aux;
			tempoCoracoesEstagio = 5;
			}
		
		caso = Cadastrocaso.carregar(teste.getIdCasoEmergencial()
				.getIdCasoEmergencial());
		if(listaQueixasPaciente.size() == 0){
			CarregarQueixas();
		}
		
		paciente = cadastroPaciente
				.carregar(caso.getPaciente().getIdPaciente());

		if(listacaracteristica.size()==0){
			CarrregarCaracteristica();
		}

		return null;
	}

	/*Carrega a lista de caracteristicas da queixa principal*/
	public String CarrregarCaracteristica() {
		starSession();
		for (Caracteristica c : caso.getQueixaPrincipal().getCaracteristica()) {
			listacaracteristica.add(c);
		}
		return null;
	}
	
	

	public String SalvarJogo() {
		starSession();
		try {
			Simulacao simulacao = (Simulacao) FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap().get("simulacao");
			condutaMedica.setSimulacao(simulacao);
			
			if(condutaMedica.getCondutaMedica()!=null){
				cadastroCondutaMedica.salvar(condutaMedica);
				jogo.setCondutaMedica(condutaMedica);
			}
			
			if( diagnostico.getTratamento()!= null){
				cadastroDiagnostico.salvar(diagnostico);
				jogo.setDiagnostico(diagnostico);
			}
			
			if(listaExamesSolicitados.size()>0){
				Set<Exame> exame = new HashSet<Exame>();
				for (Exame exameAux : listaExamesSolicitados) {
					exame.add(exameAux);
				}
				jogo.setExame(exame);
			}
			Usuario usuariologado= (Usuario) FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap().get("usuarioAluno");
			jogo.setAluno(usuariologado);
			
			jogo.setDataHoraFim(new Date());
			jogo.setSimulacao(simulacao);
			jogo.setHoraSolicitacaoExame("Hora de Início: "+formataTempo(jogo.getDataHoraInicio())+"\n"+"\n"+jogo.getHoraSolicitacaoExame());
			cadastroJogo.salvar(jogo);
			
			
		} catch (Exception e) {
			mensagem("Ocorreu um erro ao Salvar os dados!", 2);
			return null;
		}
		mensagem("O Jogo foi Salvo com Sucesso!", 1);
		jogo = new Jogo();
		condutaMedica = new  CondutaMedica();
		diagnostico =new Diagnostico();
		listaExamesSolicitados = new ArrayList<>();
		limpaCampos();
		return "telaAlunoInicio.xhtml?faces-redirect=true";
	}

	public String telaconduta() {
		return "jogoConduta.xhtml";
	}

	public String teladiagnostico() {
		return "jogoDiagnostico.xhtml";
	}

	public void mensagem(String messagem, int tipo ) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		switch(tipo){
		case 1: 
			facesMessage = new FacesMessage(facesMessage.SEVERITY_INFO, messagem, null);
			context.addMessage(null,  facesMessage);
			break;
		case 2:
			facesMessage = new FacesMessage(facesMessage.SEVERITY_ERROR, messagem, null);
			context.addMessage(null,  facesMessage);
			break;
		}
	}

	public String AdicionarListaExamesSolicitados(){
		starSession();

		Exame exm = cadastroExame.carregar(exame.getIdExame());
		listaExamesSolicitados.add(exm);
		caso = Cadastrocaso.carregar(caso.getIdCasoEmergencial());
		for (Exame exameAux : caso.getExame()) {
			if(exameAux.getIdExame()== exame.getIdExame()){
				listaExamesSolicitadosValidos.add(cadastroExame.carregar(exame.getIdExame()));
				break;
			}
		}
		Date horaAtual= new Date();
		jogo.setHoraSolicitacaoExame("Nome do exame:"+exm.getNomeExame()+"\n"+ "Hora da solicitação: "
		+formataTempo(horaAtual)+"\n"+"\n"+(jogo.getHoraSolicitacaoExame()==null ?" " : jogo.getHoraSolicitacaoExame() ));
		mensagem("O Exame foi solicitado com sucesso!", 1);
		return null;
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
	
	public void CarregarQueixas(){;
		listaQueixasPaciente.addAll(caso.getQueixa());
	}
	
	
	public void diminuiTempo() throws IOException{
		if(tempoJogo<0){
			if(tempoJogo==-2){
				jogo.setIsTimeOut(true);
				SalvarJogo();
				limpaCampos();
				FacesContext.getCurrentInstance().getExternalContext().redirect("telaAlunoInicio.xhtml");
			}else{
				tempoJogo--;	
			}	
		}else{
			if(tempoJogo==0){
				FacesMessage facesMessage = null;
				facesMessage = new FacesMessage(facesMessage.SEVERITY_INFO, "Seu Tempo Acabou!!!", null);
				RequestContext.getCurrentInstance().showMessageInDialog(facesMessage);
				tempoJogo--;
			}
			if(tempoJogo>0){
				tempoJogo--;
			}
		}
	}
	
	public void diminuiCoracao(){
		tempoAtual = tempoAtual + 1;
		if(tempoAtual == tempoCoracoes){
			tempoCoracoesEstagio --; //diminui um coração
			if(listaQueixasPaciente.size()>0){
				FacesMessage facesMessage = null;
				facesMessage = new FacesMessage(facesMessage.SEVERITY_FATAL,"Nova queixa do paciente: "+ listaQueixasPaciente.get(0).getDescricaoQueixa(), null);
				RequestContext.getCurrentInstance().showMessageInDialog(facesMessage);
				listaQueixasPaciente.remove(0);
			}
			tempoAtual = 0;
		}
		if(tempoCoracoesEstagio==0){
			FacesMessage facesMessage = null;
			facesMessage = new FacesMessage(facesMessage.SEVERITY_FATAL, "O paciente morreu", null);
			jogo.setPacienteMorreu(true);
			RequestContext.getCurrentInstance().showMessageInDialog(facesMessage);
			tempoCoracoesEstagio--;
		}

	}
	
	public void limpaCampos(){
		tempoAtual = 0;
		tempoCoracoes = 0;
		tempoCoracoesEstagio = 0;
		tempoExame = 0;
		tempoExameAtual = 0;
		tempoJogo = 0;
		jogo  = new Jogo();
		listacaracteristica = new ArrayList<>();
		listaExamesSolicitados = new ArrayList<>();
		listaExamesSolicitadosValidos= new ArrayList<>();
		listaExamesVisualizacaoDisponivel = new ArrayList<>();
		listaQueixasPaciente = new ArrayList<>();
		exameAtual = new Exame();
		exame = new Exame();
	}
	
	public void calculaTempoExame(){
		//verifica se a lista esta vazia
		if(listaExamesSolicitadosValidos.size()>0){
			//verifica se já existe algum exame sendo calculado o tempo
			if(exameAtual== null){
					exameAtual = listaExamesSolicitadosValidos.get(0);
					tempoExameAtual=tempoExameAtual +10;
				
				}
			if(tempoExameAtual==tempoExame){
						FacesMessage facesMessage = null;
						facesMessage = new FacesMessage(facesMessage.SEVERITY_INFO, "Já existe um exame pronto.", null);
						RequestContext.getCurrentInstance().showMessageInDialog(facesMessage);
						listaExamesVisualizacaoDisponivel.add(exameAtual);
						listaExamesSolicitadosValidos.remove(0);
						exameAtual = null;
						tempoExameAtual = 0;
				}else{
				tempoExameAtual= tempoExameAtual + 10;
				}
		}
			
	}
	
	public void CarregarImagemExame(int idexame){
		starSession();
		Exame exameaux = cadastroExame.carregar(idexame);
		imagemexames = null;
		imagemexames = cadastroImagemExame.carregar(exameaux.getIdImagem().getIdImagemExames());
		image = null;
		  InputStream is = new ByteArrayInputStream(imagemexames.getImagem());
		   image = new DefaultStreamedContent(is, imagemexames.getTipoImagem());
	}
	
	public String telaExames(){
		return "diagnosticus_acoes_exames.xhtml";
	}

	public int getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(int tipousuario) {
		this.tipousuario = tipousuario;
	}

	public Simulacao getSimulacao() {
		return simulacao;
	}

	public void setSimulacao(Simulacao simulacao) {
		this.simulacao = simulacao;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public CasoEmergencial getCaso() {
		return caso;
	}

	public void setCaso(CasoEmergencial caso) {
		this.caso = caso;
	}

	public List<Caracteristica> getListacaracteristica() {
		return listacaracteristica;
	}

	public void setListacaracteristica(List<Caracteristica> listacaracteristica) {
		this.listacaracteristica = listacaracteristica;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public CondutaMedica getCondutaMedica() {
		return condutaMedica;
	}

	public void setCondutaMedica(CondutaMedica condutaMedica) {
		this.condutaMedica = condutaMedica;
	}

	public int getTempoJogo() {
		return tempoJogo;
	}

	public void setTempoJogo(int tempoJogo) {
		this.tempoJogo = tempoJogo;
	}

	public int getTempoCoracoes() {
		return tempoCoracoes;
	}

	public void setTempoCoracoes(int tempoCoracoes) {
		this.tempoCoracoes = tempoCoracoes;
	}

	public int getTempoCoracoesEstagio() {
		return tempoCoracoesEstagio;
	}

	public void setTempoCoracoesEstagio(int tempoCoracoesEstagio) {
		this.tempoCoracoesEstagio = tempoCoracoesEstagio;
	}

	public int getTempoAtual() {
		return tempoAtual;
	}

	public void setTempoAtual(int tempoAtual) {
		this.tempoAtual = tempoAtual;
	}

	public int getTempoExame() {
		return tempoExame;
	}

	public void setTempoExame(int tempoExame) {
		this.tempoExame = tempoExame;
	}

	public Exame getExameAtual() {
		return exameAtual;
	}

	public void setExameAtual(Exame exameAtual) {
		this.exameAtual = exameAtual;
	}

	public List<Exame> getListaExamesVisualizacaoDisponivel() {
		return listaExamesVisualizacaoDisponivel;
	}

	public void setListaExamesVisualizacaoDisponivel(
			List<Exame> listaExamesVisualizacaoDisponivel) {
		this.listaExamesVisualizacaoDisponivel = listaExamesVisualizacaoDisponivel;
	}

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}
	

}
