package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.CadastroTipoQueixa;
import br.com.diagnosticus_action.dominio.TipoQueixa;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorTipoQueixa")
@SessionScoped
public class TratadorDeTipoQueixa {
	private TipoQueixa tipoqueixa = new TipoQueixa();
	private CadastroTipoQueixa cadastroTipoQueixa;
	private List<SelectItem> selectTipoQueixa;
	private String novoNome;

	public TratadorDeTipoQueixa() {

	}

	public void starSession() {
		this.cadastroTipoQueixa = DAOFactory.criarTipoQueixaDAO();
	}

	public void adicionarTipoQueixa() {
		this.cadastroTipoQueixa = new CadastroTipoQueixa();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroTipoQueixa.salvar(tipoqueixa);
			facesMessage = new FacesMessage("Inserido com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível inserir!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<TipoQueixa> listar() {
		this.cadastroTipoQueixa = new CadastroTipoQueixa();
		starSession();
		return this.cadastroTipoQueixa.listar();
	}

	public List<SelectItem> getSelectTipoExame() {
		this.cadastroTipoQueixa = new CadastroTipoQueixa();
		starSession();
		List<TipoQueixa> listaTipoQueixa = this.cadastroTipoQueixa.listar();
		this.selectTipoQueixa = new ArrayList<>();
		for (TipoQueixa tipoqueixa : listaTipoQueixa) {
			this.selectTipoQueixa.add(new SelectItem(tipoqueixa, tipoqueixa
					.getDescricaoTipoQueixa()));
		}
		return selectTipoQueixa;
	}

	public TipoQueixa getTipoQueixa() {
		return tipoqueixa;
	}

	public void setTipoQueixa(TipoQueixa tipoqueixa) {
		this.tipoqueixa = tipoqueixa;

	}

}
