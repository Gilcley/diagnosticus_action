package br.com.diagnosticus_action.Tratador;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.diagnosticus_action.Cadastro.CadastroCaracteristica;
import br.com.diagnosticus_action.Cadastro.CadastroTipoCaracteristica;
import br.com.diagnosticus_action.dominio.Caracteristica;
import br.com.diagnosticus_action.dominio.Queixa;
import br.com.diagnosticus_action.dominio.TipoCaracteristica;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorCaracteristica")
@SessionScoped
public class TratadorDeCaracteristica {
	private Caracteristica Caracteristica = new Caracteristica();
	private CadastroCaracteristica cadastroCaracteristica;
	private TipoCaracteristica tipoCaracteristica = new TipoCaracteristica();
	private CadastroTipoCaracteristica cadastroTipoCaracteristica;

	public TratadorDeCaracteristica() {

	}

	public void starSession() {
		this.cadastroCaracteristica = DAOFactory
				.criarCaracteristicaDAO();
		this.cadastroTipoCaracteristica= DAOFactory.criarTipoCaracteristicaDAO();
	}

	public String adicionarCaracteristica() {
		this.cadastroCaracteristica = new CadastroCaracteristica();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;	
		starSession();
		try {
			Caracteristica.setTipoCaracteristica(cadastroTipoCaracteristica.carregar(tipoCaracteristica.getIdTipoCaracteristica()));
			
			this.cadastroCaracteristica.salvar(Caracteristica);
			facesMessage = new FacesMessage("Inserido com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível inserir!");
			return null;
		} finally {
			context.addMessage(null, facesMessage);
			limpaCampos();
		}
		return null;

	}
	
	public String alterarCaracteristica() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;

		try {
			Caracteristica novacaracteristica = new Caracteristica();
			novacaracteristica = cadastroCaracteristica.carregar(Caracteristica.getIdCaracteristica());
			novacaracteristica.setDescricaoCaracteristica(Caracteristica.getDescricaoCaracteristica());;
			novacaracteristica.setTipoCaracteristica(Caracteristica.getTipoCaracteristica());;

			cadastroCaracteristica.atualizar(novacaracteristica);
			facesMessage = new FacesMessage(
					" A Característica  foi alterada com sucesso!");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível alterar a Característica!");
		} finally {
			limpaCampos();
			context.addMessage(null, facesMessage);
		}

		return null;
	}

	public List<Caracteristica> listar() {
		this.cadastroCaracteristica = new CadastroCaracteristica();
		starSession();
		return this.cadastroCaracteristica.listar();
	}

	public Caracteristica getCaracteristica() {
		return Caracteristica;
	}
	
	public String limpaCampos(){
		Caracteristica = new Caracteristica();
		tipoCaracteristica = new TipoCaracteristica();
		return null;
	}
	
	public String ConsultarCaracteristica(){
		starSession();
		this.Caracteristica = cadastroCaracteristica.carregar(Caracteristica.getIdCaracteristica());
		return null;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.Caracteristica = caracteristica;
	}

	public TipoCaracteristica getTipoCaracteristica() {
		return tipoCaracteristica;
	}

	public void setTipoCaracteristica(TipoCaracteristica tipoCaracteristica) {
		this.tipoCaracteristica = tipoCaracteristica;
	}
	
	

}
