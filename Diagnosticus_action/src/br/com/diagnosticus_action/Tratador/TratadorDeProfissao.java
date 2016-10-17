package br.com.diagnosticus_action.Tratador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.diagnosticus_action.Cadastro.CadastroNaturalidade;
import br.com.diagnosticus_action.Cadastro.CadastroProfissao;
import br.com.diagnosticus_action.dominio.Naturalidade;
import br.com.diagnosticus_action.dominio.Profissao;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorProfissao")
@SessionScoped
public class TratadorDeProfissao {
	private Profissao profissao = new Profissao();
	private CadastroProfissao cadastroProfissao;

	public TratadorDeProfissao() {

	}

	public void starSession() {
		this.cadastroProfissao = DAOFactory.criarProfissaoDAO();
	}

	public void adicionarProfissao() {
		this.cadastroProfissao = new CadastroProfissao();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroProfissao.salvar(profissao);
			facesMessage = new FacesMessage("Profissão inserida com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir a Profissão!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<Profissao> listar() {
		this.cadastroProfissao = new CadastroProfissao();
		starSession();
		return this.cadastroProfissao.listar();
	}

	public Profissao getProfissao() {
		return profissao;
	}

	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}

	
}
