package filter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher; 
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;

import br.com.diagnosticus_action.util.HibernateUtil;

public class ConexaoHibernateFilter implements Filter {
	private String page = "loginEntrar.xhtml";
	private String paginaLogin = "/Diagnosticus_action/faces/loginEntrar.xhtml";
	private String paginaPrimeiroAcesso = "/Diagnosticus_action/faces/loginPrimeiroAcesso.xhtml";
	private String paginaRecuperarSenha = "/Diagnosticus_action/faces/loginRecuperarSenha.xhtml";
	
	private SessionFactory sf;

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
		if (config.getInitParameter("page") != null) {
			page = config.getInitParameter("page");
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws ServletException, IOException {
		try {

			this.sf.getCurrentSession().beginTransaction();
			chain.doFilter(servletRequest, servletResponse);
			this.sf.getCurrentSession().getTransaction().commit();
			this.sf.getCurrentSession().close();
		} catch (Throwable ex) {
			try {
				if (this.sf.getCurrentSession().getTransaction().isActive()) {
					this.sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
			throw new ServletException(ex);
		}
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpSession session = request.getSession();

		// recupera o usuario adm logado da sessão
		Object login = session.getAttribute("UsuarioAtual");
		RequestDispatcher dispatcher = null;

		if(!(request.getRequestURI().equals(paginaLogin)||request.getRequestURI().equals(paginaPrimeiroAcesso) || request.getRequestURI().equals(paginaRecuperarSenha))){
			if (login == null) {
				// então envia para a pagina de acesso invalido
				dispatcher = servletRequest
						.getRequestDispatcher("loginEntrar.xhtml");
				dispatcher.forward(servletRequest, servletResponse);
			} 
					
		}
		
	}
}