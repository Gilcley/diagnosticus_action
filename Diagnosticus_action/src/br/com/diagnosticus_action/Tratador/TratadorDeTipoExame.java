package br.com.diagnosticus_action.Tratador;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.diagnosticus_action.Cadastro.CadastroTipoExame;
import br.com.diagnosticus_action.dominio.TipoExame;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorTipoExame")
@SessionScoped
public class TratadorDeTipoExame {
	private TipoExame tipoexame = new TipoExame();
	private CadastroTipoExame cadastroTipoExame;
	private List<SelectItem> selectTipoExame;
	private String novoNome;

	public TratadorDeTipoExame() {

	}

	public void starSession() {
		this.cadastroTipoExame = DAOFactory.criarTipoExameDAO();
	}

	public void adicionarTipoExame() {
		this.cadastroTipoExame = new CadastroTipoExame();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		try {
			this.cadastroTipoExame.salvar(tipoexame);
			facesMessage = new FacesMessage("Inserido com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível inserir!");
		} finally {
			context.addMessage(null, facesMessage);
		}

	}

	public List<TipoExame> listar() {
		this.cadastroTipoExame = new CadastroTipoExame();
		starSession();
		return this.cadastroTipoExame.listar();
	}

	public List<SelectItem> getSelectTipoExame() {
		this.cadastroTipoExame = new CadastroTipoExame();
		starSession();
		List<TipoExame> listaTipoExame = this.cadastroTipoExame.listar();
		this.selectTipoExame = new ArrayList<>();
		for (TipoExame tipoexame : listaTipoExame) {
			this.selectTipoExame.add(new SelectItem(tipoexame, tipoexame
					.getDescricaoTipoExame()));
		}
		return selectTipoExame;
	}

	public TipoExame getTipoExame() {
		return tipoexame;
	}

	public void setTipoExame(TipoExame tipoexame) {
		this.tipoexame = tipoexame;

	}
}
