package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.*;
import br.com.diagnosticus_action.dominio.EstadoCivil;
import br.com.diagnosticus_action.dominio.Genero;
import br.com.diagnosticus_action.dominio.Naturalidade;
import br.com.diagnosticus_action.dominio.Paciente;
import br.com.diagnosticus_action.dominio.Profissao;
import br.com.diagnosticus_action.dominio.Raca;
import br.com.diagnosticus_action.util.DAOFactory;

	@ManagedBean(name = "tratadorPaciente")
	@SessionScoped
	public class TratadorDePaciente {
		private Paciente paciente = new Paciente();
		
		private CadastroPaciente cadastroPaciente;
		private CadastroRaca cadastroRaca;
		private CadastroEstadoCivil cadastroEstadoCivil;
		private CadastroProfissao cadastroProfissao;
		private CadastroNaturalidade cadastroNaturalidade;
		private CadastroGenero cadastroGenero;
		
		
		private Raca raca = new Raca();
		private Genero genero = new Genero();
		private EstadoCivil estadocivil = new EstadoCivil();
		private Naturalidade naturalidade = new Naturalidade();
		private Profissao profissao = new Profissao();
		private List<SelectItem> selectPaciente;
		private String novoNome;

		public TratadorDePaciente() {

		}

		public void starSession() {
			this.cadastroPaciente = DAOFactory.criarPacienteDAO();
			
			this.cadastroEstadoCivil = DAOFactory.criarEstadoCivilDAO();
			this.cadastroGenero = DAOFactory.criarGeneroDAO();
			this.cadastroNaturalidade = DAOFactory.criarNaturalidadeDAO();
			this.cadastroProfissao = DAOFactory.criarProfissaoDAO();
			this.cadastroRaca = DAOFactory.criarRacaDAO();
		}

		public void adicionarPaciente() {
			
			
			this.cadastroPaciente = new CadastroPaciente();
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = null;
			starSession();
			
			cadastroNaturalidade.salvar(naturalidade);
			cadastroProfissao.salvar(profissao);
			
			paciente.setNaturalidade(naturalidade);
			paciente.setProfissao(profissao);
			
			paciente.setEstadoCivil(cadastroEstadoCivil.carregar(estadocivil.getIdEstadoCivil()));
			paciente.setGenero(cadastroGenero.carregar(genero.getIdGenero()));
			paciente.setRaca(cadastroRaca.carregar(raca.getIdRaca()));
			
			
			try {
				
				this.cadastroPaciente.salvar(paciente);
				facesMessage = new FacesMessage("Paciente inserido com sucesso");
			} catch (Exception e) {
				facesMessage = new FacesMessage(
						"Não foi possível inserir o Paciente!");
			} finally {
				limpaCampos();
				context.addMessage(null, facesMessage);
			}

		}

		public void alterarPaciente() {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = null;
					
			try {
				
				Naturalidade naturalidadealterar = new Naturalidade();
				Profissao profissaoalterar = new Profissao();
				naturalidadealterar = cadastroNaturalidade.carregar(paciente.getNaturalidade().getIdNaturalidade());
				profissaoalterar =cadastroProfissao.carregar(paciente.getProfissao().getIdProfissao());
				naturalidadealterar.setNaturalidade(paciente.getNaturalidade().getNaturalidade());
				profissaoalterar.setProfissao(paciente.getProfissao().getProfissao());
				cadastroNaturalidade.atualizar(naturalidadealterar);
				cadastroProfissao.atualizar(profissaoalterar);
				Paciente novopaciente = new Paciente();
				novopaciente = cadastroPaciente.carregar(paciente.getIdPaciente());
				
				novopaciente.setNaturalidade(paciente.getNaturalidade());
				novopaciente.setRaca(paciente.getRaca());
				novopaciente.setGenero(paciente.getGenero());
				novopaciente.setEstadoCivil(paciente.getEstadoCivil());
				novopaciente.setProfissao(paciente.getProfissao());
				novopaciente.setAntecedentesFamiliares(paciente.getAntecedentesFamiliares());
				novopaciente.setAntecedentesPessoais(paciente.getAntecedentesPessoais());
				novopaciente.setDataNascimento(paciente.getDataNascimento());
				novopaciente.setHda(paciente.getHda());
				novopaciente.setHistoricoPsicossocialHabitosVicios(paciente.getHistoricoPsicossocialHabitosVicios());
				novopaciente.setNome(paciente.getNome());
				
				cadastroPaciente.atualizar(novopaciente);
				facesMessage = new FacesMessage(" O Paciente foi alterado com sucesso!");
			} catch (Exception e) {
				facesMessage = new FacesMessage("Não foi possível alterar a Raça!");
			} finally {
				limpaCampos();
				context.addMessage(null, facesMessage);
			}

		}

		public void removerRaca() {
			this.cadastroPaciente = new CadastroPaciente();
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = null;
			starSession();
			try {
				this.cadastroPaciente.excluir(paciente);
				facesMessage = new FacesMessage("Raça removida com sucesso!");
			} catch (Exception e) {
				facesMessage = new FacesMessage("Não foi possível remover a Raça!");
			} finally {
				context.addMessage(null, facesMessage);
			}
		}
		
		public List<Paciente> listar() {
			this.cadastroPaciente = new CadastroPaciente();
			starSession();
			return this.cadastroPaciente.listar();
		}
		
		public String consultarPaciente(){
			this.paciente = cadastroPaciente.carregar(paciente.getIdPaciente());
			return null;
		}
		
		public void limpaCampos(){
			paciente = new Paciente();
			raca = new Raca();
			genero= new Genero();
			estadocivil = new EstadoCivil();
			naturalidade = new Naturalidade();
			profissao = new Profissao();
		}

		public List<SelectItem> getSelectGenero() {
			this.cadastroPaciente = new CadastroPaciente();
			starSession();
			List<Paciente> listaPaciente = this.cadastroPaciente.listar();
			this.selectPaciente = new ArrayList<>();
			for (Paciente paciente : listaPaciente) {
				this.selectPaciente.add(new SelectItem(paciente, paciente.getNome()));
			}
			return selectPaciente;
		}

		public Paciente getPaciente() {
			return paciente;
		}

		public void setPaciente(Paciente paciente) {
			this.paciente = paciente;
		}

		public Raca getRaca() {
			return raca;
		}

		public void setRaca(Raca raca) {
			this.raca = raca;
		}

		public Genero getGenero() {
			return genero;
		}

		public void setGenero(Genero genero) {
			this.genero = genero;
		}

		public EstadoCivil getEstadocivil() {
			return estadocivil;
		}

		public void setEstadocivil(EstadoCivil estadocivil) {
			this.estadocivil = estadocivil;
		}

		public Naturalidade getNaturalidade() {
			return naturalidade;
		}

		public void setNaturalidade(Naturalidade naturalidade) {
			this.naturalidade = naturalidade;
		}

		public Profissao getProfissao() {
			return profissao;
		}

		public void setProfissao(Profissao profissao) {
			this.profissao = profissao;
		}

		
		

}
