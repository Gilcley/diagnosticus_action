package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import br.com.diagnosticus_action.Cadastro.CadastroTurma;
import br.com.diagnosticus_action.Cadastro.CadastroUsuario;
import br.com.diagnosticus_action.dominio.Turma;
import br.com.diagnosticus_action.dominio.Usuario;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorTurma")
@SessionScoped
public class TratadorDeTurma {
	
	Turma turma = new Turma();
	Usuario aluno = new Usuario();
	List<Usuario> listaAlunos = new ArrayList<>();
	CadastroTurma cadastroTurma = new  CadastroTurma();
	
	public TratadorDeTurma (){
		listaAlunos = new ArrayList<>();
		starSession();
	}
	
	public String cadastrarTurma(){
		starSession();
		FacesMessage facesMessage = null;	
		try{
			Set<Usuario> alunos = new HashSet<Usuario>();
			for (int i = 0; i < listaAlunos.size(); i++) {
				alunos.add(listaAlunos.get(i));
			}
			turma.setAlunos(alunos);
			cadastroTurma.salvar(turma);
			facesMessage = new FacesMessage("Inserido com sucesso");
		}catch(Exception e){
			facesMessage = new FacesMessage("Não foi possível inserir!");
		}finally{
			getContext().addMessage(null, facesMessage);
			turma = new Turma();
		}
		return null;
	}
	
   public void adicionarAluno(SelectEvent event) {
	   if(aluno != null && aluno.getIdUsuario() > 0 && !listaAlunos.contains(getAluno()))
		   listaAlunos.add(getAluno());
    }
   
	public String removerAluno(Usuario aluno){
		listaAlunos.remove(aluno);
		return null;
	}
   
	public List<Turma> getTurmas() {
		starSession();
		return cadastroTurma.listar();
	}
	
	public String buscarTurmaSelecionada(){
		starSession();
		this.turma = cadastroTurma.carregar(turma.getId());
		return null;
	}
   
   
   public int getQtdAlunos(){
	   return listaAlunos.size();
   }
	
	private FacesContext getContext(){
		return FacesContext.getCurrentInstance();
	}
	
	private void starSession() {
		cadastroTurma = DAOFactory.criarTurmaDAO();
	}
	
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Usuario> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Usuario> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public Usuario getAluno() {
		return aluno;
	}

	public void setAluno(Usuario aluno) {
		this.aluno = aluno;
	}

}
