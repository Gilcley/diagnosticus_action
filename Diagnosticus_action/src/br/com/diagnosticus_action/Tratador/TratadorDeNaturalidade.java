package br.com.diagnosticus_action.Tratador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.diagnosticus_action.Cadastro.CadastroNaturalidade;
import br.com.diagnosticus_action.Cadastro.CadastroRaca;
import br.com.diagnosticus_action.dominio.Naturalidade;
import br.com.diagnosticus_action.dominio.Raca;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorNaturalidade")
@SessionScoped
public class TratadorDeNaturalidade {
	private Naturalidade naturalidade = new Naturalidade();
	private CadastroNaturalidade cadastroNaturalidade;

	public TratadorDeNaturalidade() {

	}

	public void starSession() {
		this.cadastroNaturalidade = DAOFactory.criarNaturalidadeDAO();
	}

	public void adicionarNaturalidade() {
		this.cadastroNaturalidade = new CadastroNaturalidade();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroNaturalidade.salvar(naturalidade);
			facesMessage = new FacesMessage("Naturalidade inserida com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir a Naturalidade!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public void alterarNaturalidade() {
		this.cadastroNaturalidade = new CadastroNaturalidade();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroNaturalidade.atualizar(naturalidade);
			facesMessage = new FacesMessage(
					"Naturalidade alterada com sucesso!");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível alterar a Naturalidade!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public void removerNaturalidade() {
		this.cadastroNaturalidade = new CadastroNaturalidade();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroNaturalidade.excluir(naturalidade);
			facesMessage = new FacesMessage(
					"Naturalidade removida com sucesso!");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível remover a Naturalidade!");
		} finally {
			context.addMessage(null, facesMessage);

		}
	}
	
	public List<Naturalidade> listar() {
		this.cadastroNaturalidade = new CadastroNaturalidade();
		starSession();
		return this.cadastroNaturalidade.listar();
	}

	public Naturalidade getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Naturalidade naturalidade) {
		this.naturalidade = naturalidade;
	}
	
	
}