package br.com.diagnosticus_action.Tratador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.diagnosticus_action.Cadastro.CadastroTipoCaracteristica;
import br.com.diagnosticus_action.dominio.TipoCaracteristica;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorTipoCaracteristica")
@SessionScoped
public class TratadorTipoCaracteristica {
	private TipoCaracteristica tipoCaracteristica = new TipoCaracteristica();
	private CadastroTipoCaracteristica cadastroTipoCaracteristica;

	public TratadorTipoCaracteristica() {

	}

	public void starSession() {
		this.cadastroTipoCaracteristica = DAOFactory
				.criarTipoCaracteristicaDAO();
	}

	public void adicionarTipoCaracteristica() {
		this.cadastroTipoCaracteristica = new CadastroTipoCaracteristica();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroTipoCaracteristica.salvar(tipoCaracteristica);
			facesMessage = new FacesMessage("Inserido com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível inserir!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<TipoCaracteristica> listar() {
		this.cadastroTipoCaracteristica = new CadastroTipoCaracteristica();
		starSession();
		return this.cadastroTipoCaracteristica.listar();
	}

	public TipoCaracteristica getTipoCaracteristica() {
		return tipoCaracteristica;
	}

	public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristica) {
		this.tipoCaracteristica = tipoCaracteristica;
	}

}
