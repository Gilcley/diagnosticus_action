package br.com.diagnosticus_action.Tratador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.diagnosticus_action.Cadastro.*;
import br.com.diagnosticus_action.dominio.*;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorAvaliacao")
@ViewScoped
public class TratadorDeAvaliacao {

	private Avaliacao avaliacao = new Avaliacao();
	private Usuario aluno = new Usuario();
	private Simulacao simulacaoAluno = new Simulacao();
	private Jogo jogoAvaliacao = new Jogo();

	private List<Simulacao> listaSimulacao = new ArrayList<>();

	private CadastroUsuario cadastroUsuario = new CadastroUsuario();
	private CadastroSimulacao cadastroSimulacao = new CadastroSimulacao();
	private CadastroAvaliacao cadastroAvaliacao = new CadastroAvaliacao();
	private CadastroJogo cadastroJogoAvaliacao = new CadastroJogo();

	public void starSession() {
		this.cadastroAvaliacao = DAOFactory.criarAvaliacaoDAO();
		this.cadastroSimulacao = DAOFactory.criarSimulacaoDAO();
		this.cadastroUsuario = DAOFactory.criarUsuarioDAO();
		this.cadastroJogoAvaliacao = DAOFactory.criarJogoDao();
	}

	public String carregarDetalhesSimulacao() {
		starSession();
		simulacaoAluno = cadastroSimulacao.carregar(simulacaoAluno
				.getIdSimulacao());
		Jogo jogo = new Jogo();
		jogo = cadastroJogoAvaliacao.buscarJogo(aluno.getIdUsuario(), simulacaoAluno.getIdSimulacao());
		if(jogo!=null){
			jogoAvaliacao = jogo;
		
			if(jogoAvaliacao.getIdJogo()!=null){
				this.avaliacao = cadastroAvaliacao.buscarPorAvaliacao(jogoAvaliacao.getIdJogo());
				if(avaliacao==null){
					this.avaliacao = new Avaliacao();
				}
			}
		}
		
		return null;

	}

	public String carregarDadosAluno() throws SQLException {
		starSession();
		aluno = cadastroUsuario.carregar(aluno.getIdUsuario());
		listaSimulacao = new ArrayList<>();
		List<Simulacao> listaAux = new ArrayList<>();
		List<Integer> listaIdSimulacao= cadastroSimulacao.consultarSimulacaoPorAluno(aluno.getIdUsuario());
		if(!listaIdSimulacao.isEmpty()){
			for (Integer idSimulacao : listaIdSimulacao) {
				listaAux.add(cadastroSimulacao.carregar(idSimulacao));
			}

			Usuario usuariologado= (Usuario) FacesContext.getCurrentInstance()
					.getExternalContext().getApplicationMap().get("usuariosimulacoes");
			for (Simulacao simulacao : listaAux) {
				if (simulacao.getProfessor().getIdUsuario() == usuariologado.getIdUsuario()) {
					listaSimulacao.add(simulacao);
				}
			}
		}

		return null;
	}

	public String AdicionarAvaliacao() {
		starSession();
		avaliacao.setJogo(jogoAvaliacao);
		try {
			if (avaliacao.getIdAvaliacao() == null) {
				cadastroAvaliacao.salvar(avaliacao);
			} else {
				cadastroAvaliacao.atualizar(avaliacao);
			}

		} catch (Exception e) {
			mensagem("Não foi possível inserir!", 2);
			return null;
		}
		mensagem("Avaliação inserida com sucesso!", 1);
		limpaCampos();
		return null;
	}

	public String limpaCampos() {
		jogoAvaliacao = new Jogo();
		avaliacao = new Avaliacao();
		aluno = new Usuario();
		simulacaoAluno = new Simulacao();
		return null;
	}

	public void mensagem(String messagem, int tipo) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		switch (tipo) {
		case 1:
			facesMessage = new FacesMessage(facesMessage.SEVERITY_INFO,
					messagem, null);
			context.addMessage(null, facesMessage);
			break;
		case 2:
			facesMessage = new FacesMessage(facesMessage.SEVERITY_ERROR,
					messagem, null);
			context.addMessage(null, facesMessage);
			break;
		}
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario usuario) {
		this.aluno = usuario;
	}

	public Simulacao getSimulacaoAluno() {
		return simulacaoAluno;
	}

	public void setSimulacaoAluno(Simulacao simulacao) {
		this.simulacaoAluno = simulacao;
	}

	public List<Simulacao> getListaSimulacao() {
		return listaSimulacao;
	}

	public void setListaSimulacao(List<Simulacao> listaSimulacao) {
		this.listaSimulacao = listaSimulacao;
	}

	public Jogo getJogoAvaliacao() {
		return jogoAvaliacao;
	}

	public void setJogoAvaliacao(Jogo jogoAvaliacao) {
		this.jogoAvaliacao = jogoAvaliacao;
	}

}
