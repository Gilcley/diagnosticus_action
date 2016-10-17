package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.CadastroGenero;
import br.com.diagnosticus_action.dominio.Genero;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorGenero")
@SessionScoped
public class TratadorDeGenero {
	private Genero genero = new Genero();
	private CadastroGenero cadastroGenero;
	private List<SelectItem> selectGenero;
	private String novoNome;

	public TratadorDeGenero() {

	}

	public void starSession() {
		this.cadastroGenero = DAOFactory.criarGeneroDAO();
	}

	public void adicionarRaca() {
		this.cadastroGenero = new CadastroGenero();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroGenero.salvar(genero);
			facesMessage = new FacesMessage("Genero inserida com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir o Genero!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<Genero> listar() {
		this.cadastroGenero = new CadastroGenero();
		starSession();
		return this.cadastroGenero.listar();
	}

	public List<SelectItem> getSelectGenero() {
		this.cadastroGenero = new CadastroGenero();
		starSession();
		List<Genero> listaGenero = this.cadastroGenero.listar();
		this.selectGenero = new ArrayList<>();
		for (Genero genero : listaGenero) {
			this.selectGenero.add(new SelectItem(genero, genero.getGenero()));
		}
		return selectGenero;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

}
