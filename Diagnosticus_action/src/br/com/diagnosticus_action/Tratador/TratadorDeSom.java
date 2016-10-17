package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.CadastroSom;
import br.com.diagnosticus_action.dominio.Som;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorSom")
@SessionScoped
public class TratadorDeSom {
	private Som som = new Som();
	private CadastroSom cadastroSom;
	private List<SelectItem> selectSom;
	private String novoNome;

	public TratadorDeSom() {

	}

	public void starSession() {
		this.cadastroSom = DAOFactory.criarSomDAO();
	}

	public void adicionarSom() {
		this.cadastroSom = new CadastroSom();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroSom.salvar(som);
			facesMessage = new FacesMessage("Inserido com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<Som> listar() {
		this.cadastroSom = new CadastroSom();
		starSession();
		return this.cadastroSom.listar();
	}

	public List<SelectItem> getSelectSom() {
		this.cadastroSom = new CadastroSom();
		starSession();
		List<Som> listaSom = this.cadastroSom.listar();
		this.selectSom = new ArrayList<>();
		for (Som som : listaSom) {
			this.selectSom.add(new SelectItem(som, som.getEndereco()));
		}
		return selectSom;
	}

	public Som getSom() {
		return som;
	}

	public void setSom(Som som) {
		this.som = som;
	}

}
