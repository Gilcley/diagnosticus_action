package br.com.diagnosticus_action.Tratador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.hibernate.HibernateException;

import br.com.diagnosticus_action.Cadastro.*;
import br.com.diagnosticus_action.dominio.Raca;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorRaca")
@SessionScoped
public class TratadorDeRaca {
	private Raca raca = new Raca();
	private CadastroRaca cadastroRaca;
	private List<SelectItem> selectRaca;
	private String novoNome;
	
	public TratadorDeRaca() {

	}

	public void starSession() {
		this.cadastroRaca = DAOFactory.criarRacaDAO();
	}

	public String adicionarRaca() {
		this.cadastroRaca = new CadastroRaca();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroRaca.salvar(raca);
			facesMessage = new FacesMessage("Raça inserida com sucesso");
		} catch (HibernateException se) {
			facesMessage = new FacesMessage("Não foi possível inserir a Raça!");
		}finally {
			context.addMessage(null, facesMessage);
		}
		return null;

	}

	

	public List<Raca> listar() {
		this.cadastroRaca = new CadastroRaca();
		starSession();
		return this.cadastroRaca.listar();
	}

	public List<SelectItem> getSelectRaca() {
		this.cadastroRaca = new CadastroRaca();
		starSession();
		List<Raca> listaRaca = this.cadastroRaca.listar();
		this.selectRaca = new ArrayList<>();
		for (Raca raca : listaRaca) {
			this.selectRaca.add(new SelectItem(raca, raca.getRaca()));
		}
		return selectRaca;
	}
	
	public String editar(){
		return "index";
	}

	// gets e sets
	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public String getNovoNome() {
		return novoNome;
	}

	public void setNovoNome(String novoNome) {
		this.novoNome = novoNome;
	}

	
}
