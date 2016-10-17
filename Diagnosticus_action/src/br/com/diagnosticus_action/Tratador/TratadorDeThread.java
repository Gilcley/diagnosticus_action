package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.diagnosticus_action.Cadastro.CadastroJogo;
import br.com.diagnosticus_action.dominio.Queixa;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorThread")
@SessionScoped
public class TratadorDeThread implements Runnable {
	private int tempoDeExamePronto = 10;
	private List<Queixa> listaQueixa = new ArrayList<>();
	private TratadorDeJogo tratador = new TratadorDeJogo();
	private CadastroJogo cadastroJogo = new CadastroJogo();
	private FacesContext currentInstance = null;

	public TratadorDeThread() {
	}

	public void starSession() {
		this.cadastroJogo = DAOFactory.criarJogoDao();
	}

	@Override
	public void run() {
		starSession();
		int i = 0;
		while (true) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
				i++;
				if (i == tempoDeExamePronto) {

					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public FacesContext getCurrentInstance() {
		return currentInstance;
	}

	public void setCurrentInstance(FacesContext currentInstance) {
		this.currentInstance = currentInstance;
	}

}
