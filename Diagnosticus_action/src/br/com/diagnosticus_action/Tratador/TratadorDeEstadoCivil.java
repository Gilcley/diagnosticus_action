package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.CadastroEstadoCivil;
import br.com.diagnosticus_action.Cadastro.CadastroEstadoCivil;
import br.com.diagnosticus_action.dominio.EstadoCivil;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorEstadoCivil")
@SessionScoped
public class TratadorDeEstadoCivil {
	private EstadoCivil estadocivil = new EstadoCivil();
	private CadastroEstadoCivil cadastroEstadoCivil;
	private List<SelectItem> selectEstadoCivil;
	private String novoNome;

	public TratadorDeEstadoCivil() {

	}

	public void starSession() {
		this.cadastroEstadoCivil = DAOFactory.criarEstadoCivilDAO();
	}

	public void adicionarEstadoCivil() {
		this.cadastroEstadoCivil = new CadastroEstadoCivil();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroEstadoCivil.salvar(estadocivil);
			facesMessage = new FacesMessage("Estado Civil inserida com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir a Estado Civil!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<EstadoCivil> listar() {
		this.cadastroEstadoCivil = new CadastroEstadoCivil();
		starSession();
		return this.cadastroEstadoCivil.listar();
	}

	public List<SelectItem> getSelectEstadoCivil() {
		this.cadastroEstadoCivil = new CadastroEstadoCivil();
		starSession();
		List<EstadoCivil> listaEstadoCivil = this.cadastroEstadoCivil.listar();
		this.selectEstadoCivil = new ArrayList<>();
		for (EstadoCivil estadocivil : listaEstadoCivil) {
			this.selectEstadoCivil.add(new SelectItem(estadocivil, estadocivil
					.getEstadoCivil()));
		}
		return selectEstadoCivil;
	}

	public EstadoCivil getEstadocivil() {
		return estadocivil;
	}

	public void setEstadocivil(EstadoCivil estadocivil) {
		this.estadocivil = estadocivil;
	}

}
