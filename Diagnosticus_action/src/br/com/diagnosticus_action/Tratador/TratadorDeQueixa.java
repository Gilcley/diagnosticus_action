package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.CadastroCaracteristica;
import br.com.diagnosticus_action.Cadastro.CadastroQueixa;
import br.com.diagnosticus_action.Cadastro.CadastroTipoCaracteristica;
import br.com.diagnosticus_action.Cadastro.CadastroTipoQueixa;
import br.com.diagnosticus_action.dominio.Caracteristica;
import br.com.diagnosticus_action.dominio.Naturalidade;
import br.com.diagnosticus_action.dominio.Paciente;
import br.com.diagnosticus_action.dominio.Profissao;
import br.com.diagnosticus_action.dominio.Queixa;
import br.com.diagnosticus_action.dominio.TipoCaracteristica;
import br.com.diagnosticus_action.dominio.TipoQueixa;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorQueixa")
@SessionScoped
public class TratadorDeQueixa {
	private Queixa queixa = new Queixa();
	private CadastroQueixa cadastroQueixa;

	private Caracteristica caracteristica = new Caracteristica();
	private CadastroCaracteristica cadastroCaracteristica;
	private TipoCaracteristica tipocaracteristica = new TipoCaracteristica();
	private CadastroTipoCaracteristica cadastroTipoCaracteristica;
	private List<SelectItem> selectQueixa;
	private List<Caracteristica> listacaracteristica = new ArrayList<>();
	private TipoQueixa tipoqueixa = new TipoQueixa();
	private CadastroTipoQueixa cadastrotipoqueixa = new CadastroTipoQueixa();

	public TratadorDeQueixa() {

	}

	public void starSession() {
		this.cadastroQueixa = DAOFactory.criarQueixaDAO();
		this.cadastrotipoqueixa = DAOFactory.criarTipoQueixaDAO();
		this.cadastroCaracteristica = DAOFactory.criarCaracteristicaDAO();
		this.cadastroTipoCaracteristica = DAOFactory.criarTipoCaracteristicaDAO();
	}

	public String adicionarQueixa() {
		this.cadastroQueixa = new CadastroQueixa();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();

		if (listacaracteristica.size() == 0) {
			facesMessage = new FacesMessage("Escolha pelo menos uma Característica.");
			context.addMessage(null, facesMessage);
			return null;
		}
		if (queixa.getDescricaoQueixa().isEmpty()) {
			facesMessage = new FacesMessage("O campo Descrição da queixa é obrigatório.");
			context.addMessage("error", facesMessage);
			return null;
		}
		
		Set<Caracteristica> caracteristica = new HashSet<Caracteristica>();

		for (int i = 0; i < listacaracteristica.size(); i++) {
			caracteristica.add(listacaracteristica.get(i));
		}
		queixa.setCaracteristica(caracteristica);
		try {
			this.cadastroQueixa.salvar(queixa);
			facesMessage = new FacesMessage("Queixa inserida com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir a Queixa!");
		} finally {
			context.addMessage(null, facesMessage);
		}
		limpaCampos();
		return null;

	}

	public String alterarQueixa() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;


		if (listacaracteristica.size() == 0) {
			facesMessage = new FacesMessage("Escolha pelo menos uma Característica.");
			context.addMessage(null, facesMessage);
			return null;
		}
		if (queixa.getDescricaoQueixa().isEmpty()) {
			facesMessage = new FacesMessage("O campo Descrição da queixa é obrigatório.");
			context.addMessage("error", facesMessage);
			return null;
		}
		
		Set<Caracteristica> caracteristica = new HashSet<Caracteristica>();

		for (int i = 0; i < listacaracteristica.size(); i++) {
			caracteristica.add(listacaracteristica.get(i));
		}
		try {
			Queixa novaqueixa = new Queixa();
			novaqueixa = cadastroQueixa.carregar(queixa.getIdQueixa());
			novaqueixa.setDescricaoQueixa(queixa.getDescricaoQueixa());
			novaqueixa.setCaracteristica(caracteristica);

			cadastroQueixa.atualizar(novaqueixa);
			facesMessage = new FacesMessage(
					" A Queixa  foi alterada com sucesso!");
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível alterar a Queixa!");
		} finally {
			limpaCampos();
			context.addMessage(null, facesMessage);
		}

		return null;
	}

	public String consultarQueixa() {
		listacaracteristica = new ArrayList<>();
		this.queixa = cadastroQueixa.carregar(queixa.getIdQueixa());
		for (Caracteristica c : queixa.getCaracteristica()) {
			listacaracteristica.add(c);
		}
		return null;
	}
	
	public String selecionarCaracteristica() {
		starSession();
		if (caracteristica.getIdCaracteristica() > 0) {
			Caracteristica c = new Caracteristica();
			c = cadastroCaracteristica.carregar(caracteristica.getIdCaracteristica());
			for (Caracteristica caracteristicaaux : listacaracteristica) {
				if(caracteristicaaux.getIdCaracteristica()== c.getIdCaracteristica()){
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage facesMessage = new FacesMessage("Essa Característica já foi selecionada!");
					context.addMessage(null, facesMessage);
					return null;
				}
			}
			listacaracteristica.add(c);	
			}
		return null;
	}

	public List<Queixa> listar() {
		this.cadastroQueixa = new CadastroQueixa();
		starSession();
		return this.cadastroQueixa.listar();
	}

	public void limpaCampos() {
		this.queixa = new Queixa();
		this.tipoqueixa = new TipoQueixa();
		this.listacaracteristica = new ArrayList<>();
		this.caracteristica = new Caracteristica();
	}

	public String AdicionarCaracteristica() {
		if (caracteristica.getDescricaoCaracteristica() != null) {
			listacaracteristica.add(caracteristica);
		}
		return null;
	}
	
	public String removerCaracteristica(Caracteristica caracteristica) {
		listacaracteristica.remove(caracteristica);
		return null;
	}

	public Queixa getQueixa() {
		return queixa;
	}

	public void setQueixa(Queixa queixa) {
		this.queixa = queixa;
	}

	public TipoQueixa getTipoqueixa() {
		return tipoqueixa;
	}

	public void setTipoqueixa(TipoQueixa tipoqueixa) {
		this.tipoqueixa = tipoqueixa;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public TipoCaracteristica getTipocaracteristica() {
		return tipocaracteristica;
	}

	public void setTipocaracteristica(TipoCaracteristica tipocaracteristica) {
		this.tipocaracteristica = tipocaracteristica;
	}

	public List<Caracteristica> getListacaracteristica() {
		return listacaracteristica;
	}

	public void setListacaracteristica(List<Caracteristica> listacaracteristica) {
		this.listacaracteristica = listacaracteristica;
	}
	
	
	
	

}
