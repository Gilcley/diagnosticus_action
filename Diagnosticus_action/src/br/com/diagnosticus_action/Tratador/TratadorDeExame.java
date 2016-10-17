package br.com.diagnosticus_action.Tratador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.diagnosticus_action.Cadastro.CadastroExame;
import br.com.diagnosticus_action.Cadastro.CadastroImagemExame;
import br.com.diagnosticus_action.Cadastro.CadastroTipoExame;
import br.com.diagnosticus_action.dominio.Exame;
import br.com.diagnosticus_action.dominio.ImagemExames;
import br.com.diagnosticus_action.dominio.TipoExame;
import br.com.diagnosticus_action.util.DAOFactory;

@ManagedBean(name = "tratadorExame")
@SessionScoped
public class TratadorDeExame {
	private Exame exame = new Exame();
	private CadastroExame cadastroExame;
	private List<SelectItem> selectExame;
	private ImagemExames imagemexames = new ImagemExames();
	private CadastroImagemExame cadastroimagem = new CadastroImagemExame();
	private TipoExame tipoexame = new TipoExame();
	private CadastroTipoExame cadastrotipoexame = new CadastroTipoExame();

	private StreamedContent image;

	public TratadorDeExame() {

	}

	public void starSession() {
		this.cadastroExame = DAOFactory.criarExameDAO();
		this.cadastrotipoexame = DAOFactory.criarTipoExameDAO();
		this.cadastroimagem = DAOFactory.criarImagemExameDAO();
	}

	public String adicionarExame() {
		this.cadastroExame = new CadastroExame();
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;
		starSession();
		cadastroimagem.salvar(imagemexames);

		exame.setIdImagem(imagemexames);
		exame.setIdTipoExame(cadastrotipoexame.carregar(tipoexame
				.getIdTipoExame()));

		try {
			this.cadastroExame.salvar(exame);
			facesMessage = new FacesMessage("Exame inserido com sucesso");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível inserir o Exame!");
		} finally {
			context.addMessage(null, facesMessage);
			limpaCampos();
		}

		return null;

	}

	public String alterarExame() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = null;

		try {
			Exame novoexame = new Exame();
			
			if(imagemexames != null){
			ImagemExames novaimagem = new ImagemExames();
			novaimagem = cadastroimagem.carregar(imagemexames.getIdImagemExames());
			novaimagem.setImagem(imagemexames.getImagem());
			novaimagem.setTipoImagem(imagemexames.getTipoImagem());
			cadastroimagem.atualizar(novaimagem);
			novoexame.setIdImagem(novaimagem);
			}
			
			novoexame = cadastroExame.carregar(exame.getIdExame());
			novoexame.setIdTipoExame(exame.getIdTipoExame());
			novoexame.setNomeExame(exame.getNomeExame());
			novoexame.setRaso(exame.getRaso());
			cadastroExame.atualizar(novoexame);
			facesMessage = new FacesMessage(
					" O Exame  foi alterado com sucesso!");
		} catch (Exception e) {
			facesMessage = new FacesMessage("Não foi possível alterar o Exame!");
		} finally {
			limpaCampos();
			context.addMessage(null, facesMessage);
		}

		return null;
	}

	public void limpaCampos() {
		exame = new Exame();
		tipoexame = new TipoExame();
		imagemexames= new ImagemExames();
		image = null;
	}

	public String consultarExame() {
		this.exame = cadastroExame.carregar(exame.getIdExame());
		this.imagemexames = cadastroimagem.carregar(exame.getIdImagem().getIdImagemExames());
		this.tipoexame = cadastrotipoexame.carregar(exame.getIdTipoExame()
				.getIdTipoExame());
		init();
		return null;
	}
	
	  public void init() {
		  image = null;
		  InputStream is = new ByteArrayInputStream(imagemexames.getImagem());
		   image = new DefaultStreamedContent(is, imagemexames.getTipoImagem());
	    }
	  
	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}

	public List<Exame> listar() {
		this.cadastroExame = new CadastroExame();
		starSession();
		return this.cadastroExame.listar();
	}

	public List<SelectItem> getSelectExame() {
		this.cadastroExame = new CadastroExame();
		starSession();
		List<Exame> listaExame = this.cadastroExame.listar();
		this.selectExame = new ArrayList<>();
		for (Exame exame : listaExame) {
			this.selectExame.add(new SelectItem(exame, exame.getNomeExame()));
		}
		return selectExame;
	}

	public void uploadImagem(FileUploadEvent event) {
		imagemexames.setImagem(null);
		imagemexames.setTipoImagem(null);
		imagemexames.setImagem(event.getFile().getContents());
		imagemexames.setTipoImagem(event.getFile().getContentType());
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public TipoExame getTipoexame() {
		return tipoexame;
	}

	public void setTipoexame(TipoExame tipoexame) {
		this.tipoexame = tipoexame;
	}

}
