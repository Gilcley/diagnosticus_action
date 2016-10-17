package br.com.diagnosticus_action.Relatorio;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.diagnosticus_action.Cadastro.CadastroAvaliacao;
import br.com.diagnosticus_action.dominio.Avaliacao;

public class JasperReports {
	private CadastroAvaliacao cadastroAvaliacao = new CadastroAvaliacao();

	 public void executeReport() throws JRException {
		 
			
			List<Avaliacao> listaAvaliacao = new ArrayList<>();
			Relatorio relatorio = new Relatorio();
			
			List<Relatorio> lista = new ArrayList<>();
			
			
			listaAvaliacao = cadastroAvaliacao.listar();
//			
//			for (Avaliacao avaliacao2 : listaAvaliacao) {
//				relatorio = new Relatorio();
//				relatorio.setNomeAluno(avaliacao2.getJogo().getSimulacao().getIdUsuario().getNome());
//				relatorio.setNotaAluno(avaliacao2.getNota());
//				relatorio.setDescricaoSimulacao(avaliacao2.getJogo().getSimulacao().getDescricaoSimulacao());
//				relatorio.setMatriculaAluno(avaliacao2.getJogo().getSimulacao().getIdUsuario().getMatricula());
//				lista.add(relatorio);
//			}
			
			
			
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	 
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
	 
	        InputStream reportStream = facesContext.getExternalContext().getResourceAsStream("/br/com/diagnosticus_action/Relatorio/relatorioAvaliacao.jasper");
	 
	        response.setContentType("application/pdf");
	 
	        response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
	 
	        try {
	            ServletOutputStream servletOutputStream = response.getOutputStream();
	 
	            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(lista);
	 
	            JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, null, datasource);
	 
	            servletOutputStream.flush();
	            servletOutputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally{
	            facesContext.responseComplete();
	        }
	    }
}
