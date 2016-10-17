package br.com.diagnosticus_action.Tratador;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Array;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.com.diagnosticus_action.Cadastro.CadastroAvaliacao;
import br.com.diagnosticus_action.Cadastro.CadastroJogo;
import br.com.diagnosticus_action.Cadastro.CadastroSimulacao;
import br.com.diagnosticus_action.Cadastro.CadastroTipoUsuario;
import br.com.diagnosticus_action.Cadastro.CadastroUsuario;
import br.com.diagnosticus_action.dominio.Avaliacao;
import br.com.diagnosticus_action.dominio.Jogo;
import br.com.diagnosticus_action.dominio.Simulacao;
import br.com.diagnosticus_action.dominio.TipoDeUsuario;
import br.com.diagnosticus_action.dominio.TipoUsuario;
import br.com.diagnosticus_action.dominio.Usuario;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorUsuario")
@SessionScoped
public class TratadorDeUsuario {
	private Usuario usuario = new Usuario();
	private Usuario usuarioLogado = new Usuario();
	private CadastroUsuario cadastroUsuario;
	private String confirmarSenha;
	private Simulacao simulacao = new Simulacao();
	private Avaliacao avaliacao = new Avaliacao();
	
	 private UploadedFile imagemPerfilUsuario;
	private StreamedContent image;

	private TipoUsuario tipousuario = new TipoUsuario();

	private List<Simulacao> listaSimulacoes = new ArrayList<>();
	private List<Simulacao> listaTodasSimulacoes = new ArrayList<>();
	private CadastroSimulacao cadastroSimulacao = new CadastroSimulacao();
	private CadastroTipoUsuario cadastrotipousuario = new CadastroTipoUsuario();
	private CadastroAvaliacao cadastroAvaliacao = new CadastroAvaliacao();
	private CadastroJogo cadastroJogo = new CadastroJogo();

	public String iniciarUsuario(Usuario usuario) throws SQLException {
		starSession();
		this.usuario = new Usuario();
		this.usuario = usuario;
		carregaSimulacoes();
		if(usuario.getImagemUsuario()!=null){
			InputStream is = new ByteArrayInputStream(usuario.getImagemUsuario());
			image = new DefaultStreamedContent(is, usuario.getTipoImagemUsuario());
		}
		return null;
	}

	public void starSession() {
		this.cadastroJogo = DAOFactory.criarJogoDao();
		this.cadastroUsuario = DAOFactory.criarUsuarioDAO();
		this.cadastrotipousuario = DAOFactory.criarTipoUsuarioDAO();
		this.cadastroSimulacao = DAOFactory.criarSimulacaoDAO();
	}

	public String navTelaAlunoPerfil(){
		return "telaAlunoPerfil";
	}
	
	public String adicionarUsuario() {
		this.cadastroUsuario = new CadastroUsuario();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		if (!isValidaMatricula()) {
			facesMessage = new FacesMessage(
					"Já existe um usuário cadastrado com essa matrìcula!");
			context.addMessage(null, facesMessage);
			return null;
		}
		try {
			usuario.setEmail("0");
			usuario.setSenha("0");
			usuario.setApelido("0");
			usuario.setTipoUsuario(cadastrotipousuario.carregar(tipousuario
					.getIdTipoUsuario()));
			this.cadastroUsuario.salvar(usuario);
			facesMessage = new FacesMessage("Inserido com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível inserir!");
		} finally {
			context.addMessage(null, facesMessage);
			limpaCampos();
		}
		return null;

	}

	/* Cadastra o email, senha e apelido na tela de primeiro acesso */
	public String CadastrarUsuario() {
		starSession();
		Usuario usuariologado = cadastroUsuario.buscarPorMatricula(usuario
				.getMatricula());
		if (usuariologado == null) {
			mensagemErro("Esta matrícula não está cadastrada!");
			return null;
		}

		if (usuariologado.getEmail().equalsIgnoreCase(usuario.getEmail())) {
			mensagemErro("Esse email já está cadastrado");
			return null;
		}

		// if(usuariologado.getApelido().equalsIgnoreCase(usuario.getApelido())){
		// mensagemErro("Já existe um usuário com esse apelido!");
		// return null;
		// }

		if (!isConfirmasenha()) {
			mensagemErro("As senhas não são iguais!");
			return null;
		}
		if (usuariologado.getSenha().length() > 1) {
			mensagemErro("Este usuário já está cadastrado!");
			return null;
		}
		try {
			usuariologado.setSenha(CriptografarSenhaMD5(usuario.getSenha()));
			usuariologado.setEmail(usuario.getEmail());
			usuariologado.setApelido(usuario.getApelido());
			cadastroUsuario.atualizar(usuariologado);
		} catch (Exception e) {
			mensagemErro("Não foi possível cadastrar!");
		}
		mensagemErro("Cadastrado com sucesso!");
		limpaCampos();
		return null;
	}

	public void mensagemErro(String messagem) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		facesMessage = new FacesMessage(messagem);
		context.addMessage(null, facesMessage);
	}

	public String telaJogo() {
		starSession();
		simulacao = cadastroSimulacao.carregar(simulacao.getIdSimulacao());
		FacesContext.getCurrentInstance().getExternalContext()
				.getApplicationMap().put("simulacao", simulacao);
		Date datainicio = new Date();
		FacesContext.getCurrentInstance().getExternalContext()
				.getApplicationMap().put("horainicio", datainicio);

		// TratadorDeThread t = new TratadorDeThread();
		//
		// Thread executor = new Thread(t);
		// t.setCurrentInstance(FacesContext.getCurrentInstance());
		//
		//
		// executor.start();
		for (Simulacao sim : listaSimulacoes) {
			if(sim.getIdSimulacao()== simulacao.getIdSimulacao()){
				listaSimulacoes.remove(sim);
				break;
			}
		}
		return "diagnosticus_index.xhtml?faces-redirect=true";
	}

	public String alterarUsuario() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;

		if (!isValidaMatricula()) {
			facesMessage = new FacesMessage(
					"Já existe um usuário cadastrado com essa matrìcula!");
			context.addMessage(null, facesMessage);
			return null;
		}

		try {
			Usuario novousuario = new Usuario();
			novousuario = cadastroUsuario.carregar(usuario.getIdUsuario());
			novousuario.setMatricula(usuario.getMatricula());
			novousuario.setNome(usuario.getNome());
			novousuario.setTipoUsuario(usuario.getTipoUsuario());
			novousuario.setApelido(usuario.getApelido());
			novousuario.setEmail(usuario.getEmail());

			cadastroUsuario.atualizar(novousuario);
			facesMessage = new FacesMessage(
					" O Usuário  foi alterado com sucesso!");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível alterar o Usuário!");
		} finally {
			limpaCampos();
			context.addMessage(null, facesMessage);
		}

		return null;
	}

	public String consultarUsuario() {
		this.usuario = cadastroUsuario.carregar(usuario.getIdUsuario());
		return null;
	}

	public String logar() throws NoSuchAlgorithmException, SQLException {
		starSession();
		Usuario usuariologado = cadastroUsuario.buscar(
				this.usuarioLogado.getMatricula(),
				CriptografarSenhaMD5(this.usuarioLogado.getSenha()));
		if (usuariologado == null
				|| usuariologado.getSenha().equalsIgnoreCase("0")) {
			mensagemErro("Usuário ou senha incorretos!");
			return null;
		}
		TipoDeUsuario t = new TipoDeUsuario();
		
		 liberarAcesso(usuariologado);
		if (usuariologado.getTipoUsuario().getTipoUsuario()
				.equalsIgnoreCase("professor")) {
			
			//passa para o bean de simulacoes o usuariologado
			FacesContext.getCurrentInstance().getExternalContext()
			.getApplicationMap().put("usuariosimulacoes", usuariologado);
			
			if(usuariologado.getImagemUsuario()!=null){
				 InputStream is = new ByteArrayInputStream(usuariologado.getImagemUsuario());
				 image = new DefaultStreamedContent(is, usuariologado.getTipoImagemUsuario());	
			}
			this.usuarioLogado = usuariologado;
			return "inicial.xhtml";
		}

		if (usuariologado.getTipoUsuario().getTipoUsuario()
				.equalsIgnoreCase("aluno")) {
			iniciarUsuario(usuariologado);
			
			
			FacesContext.getCurrentInstance().getExternalContext()
			.getApplicationMap().put("usuarioAluno", usuariologado);
			return "telaAlunoInicio.xhtml";
		}
		return null;

	}
	
	public void CarregarImagem(){
		if(usuario.getImagemUsuario()!=null){
			InputStream is = new ByteArrayInputStream(usuario.getImagemUsuario());
			 image = new DefaultStreamedContent(is, usuario.getTipoImagemUsuario());	
		}
		if(usuarioLogado.getImagemUsuario()!=null){
			InputStream is = new ByteArrayInputStream(usuarioLogado.getImagemUsuario());
			 image = new DefaultStreamedContent(is, usuarioLogado.getTipoImagemUsuario());
		}
	}
	
	public String consultarNotas(){
		Jogo jogo = new Jogo();
		CadastroJogo cadastrojogo = new CadastroJogo();
		cadastrojogo = DAOFactory.criarJogoDao();
		cadastroAvaliacao = DAOFactory.criarAvaliacaoDAO();
		
		jogo =cadastrojogo.buscarJogo(usuario.getIdUsuario(), simulacao.getIdSimulacao());
		if(jogo==null){
			mensagemErro("Esta simulação não foi jogada.");
			return null;
		}
		avaliacao = cadastroAvaliacao.buscarPorAvaliacao(jogo.getIdJogo());
		if(avaliacao== null){
			mensagemErro("O professor não avaliou as respostas desta simulação.");
		}
		return null;
	}
	

	public String liberarAcesso(Usuario usuariologado) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);

		session.setAttribute("UsuarioAtual", usuariologado);
		return null;
	}

	public String primeiroAcesso() {
		Usuario temporario = new Usuario();
		temporario.setNome("temporario");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);

		session.setAttribute("UsuarioAtual", temporario);
		return null;
	}

	public String sair() {
		limpaCampos();
		usuarioLogado = new Usuario();
		liberarAcesso(null);
		return "loginEntrar.xhtml";
	}

	public boolean isValidaMatricula() {
		List<Usuario> u = listar();
		for (Usuario usuario : u) {
			if (usuario.getMatricula().equalsIgnoreCase(
					this.usuario.getMatricula())) {
				return false;
			}
		}

		return true;
	}

	public void limpaCampos() {
		usuario = new Usuario();
		tipousuario = new TipoUsuario();
		listaSimulacoes = new ArrayList<>();
		listaTodasSimulacoes = new  ArrayList<>();
		simulacao = new Simulacao();
		confirmarSenha = null;
		avaliacao = new Avaliacao();

	}

	/* gera uma cadeia de caracteres relacionado com o password passado */
	public String CriptografarSenhaMD5(String password)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));

		return String.format("%32x", hash);
	}

	public void carregaSimulacoes() throws SQLException {
		listaSimulacoes = new ArrayList<>();
		List<Integer> listaAux = cadastroSimulacao.consultarSimulacaoPorAluno(usuario.getIdUsuario());
		Simulacao simAux = new Simulacao();
		Jogo jogo = new Jogo();
		starSession();
		if (listaAux.size() > 0) {
			for (Integer Idsimulacao : listaAux) {
				simAux = cadastroSimulacao.carregar(Idsimulacao);
				listaTodasSimulacoes.add(simAux);
				
				jogo = cadastroJogo.buscarJogo(usuario.getIdUsuario(), simAux.getIdSimulacao());
				
				//verifica se existe um cadastro de jogo, caso não é pq o usuario n jogou
				if(jogo==null){
					listaSimulacoes.add(simAux);
				}
			}
		}
	}

	public boolean isConfirmasenha() {
		if (confirmarSenha.equals(usuario.getSenha())) {
			return true;
		}
		usuario.setSenha(null);
		confirmarSenha = null;
		return false;
	}

	public List<Usuario> listar() {
		this.cadastroUsuario = new CadastroUsuario();
		starSession();
		return this.cadastroUsuario.listar();
	}

	public List<Usuario> listarAlunos() {
		this.cadastroUsuario = new CadastroUsuario();
		starSession();
		List<Usuario> listaAluno = new ArrayList<>();
		for (Usuario usuario : this.cadastroUsuario.listar()) {
			if (usuario.getTipoUsuario().getIdTipoUsuario() == 2) {
				listaAluno.add(usuario);
			}
		}

		return listaAluno;
	}
	
	public void uploadImagemUsuarioPerfil(FileUploadEvent event) throws IOException {
		usuario.setImagemUsuario(event.getFile().getContents());
		usuario.setTipoImagemUsuario(event.getFile().getContentType());
		starSession();
		try{
			cadastroUsuario.atualizar(usuario);
			mensagemErro("Atualizado com sucesso");

				FacesContext.getCurrentInstance().getExternalContext().redirect("telaAlunoInicio.xhtml");

			
		}catch(Exception e){
			mensagemErro("Não foi possível atualizar o perfil!");
		}
	}
	
    public List<Usuario> autoCompleteUsuario(String nome) {
        starSession();
        return cadastroUsuario.findUsuarioByNome(nome);
    }
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

	}

	public TipoUsuario getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(TipoUsuario tipousuario) {
		this.tipousuario = tipousuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public List<Simulacao> getListaSimulacoes() {
		return listaSimulacoes;
	}

	public void setListaSimulacoes(List<Simulacao> listaSimulacoes) {
		this.listaSimulacoes = listaSimulacoes;
	}

	public Simulacao getSimulacao() {
		return simulacao;
	}

	public void setSimulacao(Simulacao simulacao) {
		this.simulacao = simulacao;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Simulacao> getListaTodasSimulacoes() {
		return listaTodasSimulacoes;
	}

	public void setListaTodasSimulacoes(List<Simulacao> listaTodasSimulacoes) {
		this.listaTodasSimulacoes = listaTodasSimulacoes;
	}

	public UploadedFile getImagemPerfilUsuario() {
		return imagemPerfilUsuario;
	}

	public void setImagemPerfilUsuario(UploadedFile imagemPerfilUsuario) {
		this.imagemPerfilUsuario = imagemPerfilUsuario;
	}

	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}
	
	
	
}
