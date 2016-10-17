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

import br.com.diagnosticus_action.Cadastro.CadastroCID;
import br.com.diagnosticus_action.Cadastro.CadastroCasosEmergenciais;
import br.com.diagnosticus_action.Cadastro.CadastroExame;
import br.com.diagnosticus_action.Cadastro.CadastroPaciente;
import br.com.diagnosticus_action.Cadastro.CadastroQueixa;
import br.com.diagnosticus_action.dominio.CID;
import br.com.diagnosticus_action.dominio.CasoEmergencial;
import br.com.diagnosticus_action.dominio.Exame;
import br.com.diagnosticus_action.dominio.Paciente;
import br.com.diagnosticus_action.dominio.Queixa;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorCasoEmergencial")
@SessionScoped
public class TratadorDeCasoEmergencial {

	private CasoEmergencial casoemergencial = new CasoEmergencial();
	private CadastroCasosEmergenciais cadastroCasoEmergencial;
	private List<SelectItem> selectCasoEmergencial;

	private Queixa queixa = new Queixa();
	private Queixa queixaPrincipal = new Queixa();
	private CadastroQueixa cadastroqueixa = new CadastroQueixa();
	private Paciente paciente = new Paciente();
	private CadastroPaciente cadastropaciente = new CadastroPaciente();
	private Exame exame = new Exame();
	private CadastroExame cadastroexame = new CadastroExame();
	private CadastroCID cadastroCid = new CadastroCID();

	private List<Queixa> listaQueixaPermitidas = new ArrayList<>();
	private List<Exame> listaExames = new ArrayList();
	private List<Queixa> listaqueixa = new ArrayList();

	public TratadorDeCasoEmergencial() {

	}

	public void starSession() {
		this.cadastroCasoEmergencial = DAOFactory.criarCasoEmergencialDAO();
		this.cadastroqueixa = DAOFactory.criarQueixaDAO();
		this.cadastropaciente = DAOFactory.criarPacienteDAO();
		this.cadastroexame = DAOFactory.criarExameDAO();
		this.cadastroCid =  DAOFactory.criarCidDAO();
	}

	public String adicionarCasoEmergencial() {
		this.cadastroCasoEmergencial = new CadastroCasosEmergenciais();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		if (paciente.getIdPaciente() < 1) {
			facesMessage = new FacesMessage("O paciente não pode ser vazio.");
			context.addMessage(null, facesMessage);
			return null;
		}
		if (!verificaCampos()) {
			return null;
		}
		if (listaExames.size() == 0) {
			facesMessage = new FacesMessage("Escolha pelo menos um exame.");
			context.addMessage(null, facesMessage);
			return null;
		}
		try {
			if (listaqueixa.size() > 0) {
				Set<Queixa> queixa = new HashSet<Queixa>();
				for (int i = 0; i < listaqueixa.size(); i++) {
					queixa.add(listaqueixa.get(i));
				}
				casoemergencial.setQueixa(queixa);

			}
			casoemergencial.setPaciente(cadastropaciente.carregar(paciente
					.getIdPaciente()));
			casoemergencial.setQueixaPrincipal(cadastroqueixa
					.carregar(queixaPrincipal.getIdQueixa()));
			Set<Exame> exame = new HashSet<Exame>();

			for (int i = 0; i < listaExames.size(); i++) {
				exame.add(listaExames.get(i));
			}

			casoemergencial.setExame(exame);

			this.cadastroCasoEmergencial.salvar(casoemergencial);
			facesMessage = new FacesMessage(
					"Caso Emergencial inserido com sucesso");
			limpaCampos();
		} catch (Exception e) {
			facesMessage = new FacesMessage(
					"Não foi possível inserir o Caso Emergencial!");
		} finally {
			context.addMessage(null, facesMessage);
		}
		return null;
	}

	public String alterarCasoEmergencial() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;

		if (casoemergencial.getPaciente().getIdPaciente() < 1) {
			facesMessage = new FacesMessage("O paciente não pode ser vazio.");
			context.addMessage(null, facesMessage);
			return null;
		}
		if (!verificaCampos()) {
			return null;
		}
		if (listaExames.size() == 0) {
			facesMessage = new FacesMessage("Escolha pelo menos um exame.");
			context.addMessage(null, facesMessage);
			return null;
		}

		try {

			CasoEmergencial novocaso = cadastroCasoEmergencial
					.carregar(casoemergencial.getIdCasoEmergencial());

			novocaso.setDadosExameFisico(casoemergencial.getDadosExameFisico());
			novocaso.setDescricaoCaso(casoemergencial.getDescricaoCaso());
			novocaso.setCid(casoemergencial.getCid());
			novocaso.setPaciente(cadastropaciente.carregar(casoemergencial
					.getPaciente().getIdPaciente()));

			Set<Exame> exame = new HashSet<Exame>();

			for (int i = 0; i < listaExames.size(); i++) {
				exame.add(listaExames.get(i));
			}

			Set<Queixa> queixa = new HashSet<Queixa>();
			if (listaqueixa.size() > 0) {
				for (int i = 0; i < listaqueixa.size(); i++) {
					queixa.add(listaqueixa.get(i));
				}
				novocaso.setQueixa(queixa);
			}else{
				novocaso.setQueixa(queixa);
			}
			
			novocaso.setQueixaPrincipal(cadastroqueixa
					.carregar(queixaPrincipal.getIdQueixa()));

			novocaso.setExame(exame);

			cadastroCasoEmergencial.atualizar(novocaso);
			facesMessage = new FacesMessage(
					" O Paciente foi alterado com sucesso!");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível alterar a Raça!");
		} finally {
			limpaCampos();
			context.addMessage(null, facesMessage);
		}
		return null;

	}

	public List<CasoEmergencial> listar() {
		this.cadastroCasoEmergencial = new CadastroCasosEmergenciais();
		starSession();
		return this.cadastroCasoEmergencial.listar();
	}

	public String removerQueixa(Queixa queixa) {
		listaqueixa.remove(queixa);
		return null;
	}

	public String removerExame(Exame exame) {
		listaExames.remove(exame);
		return null;
	}

	public void limpaCampos() {
		paciente = new Paciente();
		queixa = new Queixa();
		exame = new Exame();
		casoemergencial = new CasoEmergencial();
		listaExames = new ArrayList<>();
		listaqueixa = new ArrayList<>();
		cadastroexame = new CadastroExame();
		cadastroqueixa = new CadastroQueixa();
		queixaPrincipal = new Queixa();
		starSession();
	}

	public boolean verificaCampos() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		if (casoemergencial.getDescricaoCaso().isEmpty()) {
			facesMessage = new FacesMessage(
					"A descrição do caso não pode ser vazia.");
			context.addMessage(null, facesMessage);
			return false;
		}
		if (casoemergencial.getDadosExameFisico().isEmpty()) {
			facesMessage = new FacesMessage(
					"Os dados do Exame fisico não pode ser vazio.");
			context.addMessage(null, facesMessage);
			return false;
		}
		return true;
	}

	public String consultarCasoEmergencial() {
		starSession();
		this.casoemergencial = cadastroCasoEmergencial
				.carregar(this.casoemergencial.getIdCasoEmergencial());
		listaExames = new ArrayList<>();
		listaqueixa = new ArrayList<>();
		listaQueixaPermitidas = new ArrayList<>();
		List<Queixa> aux = cadastroqueixa.listar();
		for (Exame exame : casoemergencial.getExame()) {
			listaExames.add(exame);
		}

		for (Queixa queixa : casoemergencial.getQueixa()) {
			listaqueixa.add(queixa);
		}
		if (casoemergencial.getQueixaPrincipal() != null) {
			queixaPrincipal = casoemergencial.getQueixaPrincipal();
			for (Queixa q : aux) {
				if (q.getIdQueixa() != queixaPrincipal.getIdQueixa()) {
					listaQueixaPermitidas.add(q);
				}
			}
		}

		return null;
	}

	public String selecionarExame() {
		starSession();
		if (exame.getIdExame() > 0) {
			Exame e = new Exame();
			e = cadastroexame.carregar(exame.getIdExame());
			for (Exame exameaux : listaExames) {
				if (exameaux.getIdExame() == e.getIdExame()) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage facesMessage = new FacesMessage(
							"Esse exame já foi selecionado!");
					context.addMessage(null, facesMessage);
					return null;
				}
			}
			listaExames.add(e);
		}
		return null;
	}

	public String selecionarQueixa() {
		starSession();
		listaQueixaPermitidas = new ArrayList<>();
		listaExames = new ArrayList<>();
		listaqueixa = new ArrayList<>();
		List<Queixa> aux = cadastroqueixa.listar();
		;
		for (Queixa q : aux) {
			if (q.getIdQueixa() != queixaPrincipal.getIdQueixa()) {
				listaQueixaPermitidas.add(q);
			}
		}
		return null;
	}

	public String selecionarQueixasSecundarias() {
		starSession();
		if (queixa.getIdQueixa() > 0) {
			Queixa q = new Queixa();
			q = cadastroqueixa.carregar(queixa.getIdQueixa());
			for (Queixa queixaaux : listaqueixa) {
				if (queixaaux.getIdQueixa() == q.getIdQueixa()) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage facesMessage = new FacesMessage(
							"Essa queixa já foi selecionada!");
					context.addMessage(null, facesMessage);
					return null;
				}
			}
			listaqueixa.add(q);
		}
		return null;
	}
	
    public List<CID> autoCompleteCID(String descricao) {
        starSession();
        return cadastroCid.findCidByDescricao(descricao);
    }

	public CasoEmergencial getCasoEmergencial() {
		return casoemergencial;
	}

	public void setGenero(CasoEmergencial casoemergencial) {
		this.casoemergencial = casoemergencial;
	}

	public Queixa getQueixa() {
		return queixa;
	}

	public void setQueixa(Queixa queixa) {
		this.queixa = queixa;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<Exame> getListaExames() {
		return listaExames;
	}

	public void setListaExames(List<Exame> listaExames) {
		this.listaExames = listaExames;
	}

	public List<Queixa> getListaqueixa() {
		return listaqueixa;
	}

	public void setListaqueixa(List<Queixa> listaqueixa) {
		this.listaqueixa = listaqueixa;
	}

	public Queixa getQueixaPrincipal() {
		return queixaPrincipal;
	}

	public void setQueixaPrincipal(Queixa queixaPrincipal) {
		this.queixaPrincipal = queixaPrincipal;
	}

	public List<Queixa> getListaQueixaPermitidas() {
		return listaQueixaPermitidas;
	}

	public void setListaQueixaPermitidas(List<Queixa> listaQueixaPermitidas) {
		this.listaQueixaPermitidas = listaQueixaPermitidas;
	}

}
