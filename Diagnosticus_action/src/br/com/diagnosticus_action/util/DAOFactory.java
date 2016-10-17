package br.com.diagnosticus_action.util;

import br.com.diagnosticus_action.Cadastro.*;

public class DAOFactory {

	public static CadastroUsuario criarUsuarioDAO() {
		CadastroUsuario usuarioDAO = new CadastroUsuario();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}

	public static CadastroCasosEmergenciais criarCasoEmergencialDAO() {
		CadastroCasosEmergenciais casoemergencialDAO = new CadastroCasosEmergenciais();
		casoemergencialDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return casoemergencialDAO;
	}
	
	public static CadastroEstadoCivil criarEstadoCivilDAO() {
		CadastroEstadoCivil estadocivilDAO = new CadastroEstadoCivil();
		estadocivilDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return estadocivilDAO;
	}
	
	public static CadastroExame criarExameDAO() {
		CadastroExame exameDAO = new CadastroExame();
		exameDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return exameDAO;
	}
	
	public static CadastroGenero criarGeneroDAO() {
		CadastroGenero generoDAO = new CadastroGenero();
		generoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return generoDAO;
	}
	
	public static CadastroImagemExame criarImagemExameDAO() {
		CadastroImagemExame imagemexameDAO = new CadastroImagemExame();
		imagemexameDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return imagemexameDAO;
	}
	
	public static CadastroNaturalidade criarNaturalidadeDAO() {
		CadastroNaturalidade naturalidadeDAO = new CadastroNaturalidade();
		naturalidadeDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return naturalidadeDAO;
	}
	
	public static CadastroPaciente criarPacienteDAO() {
		CadastroPaciente pacienteDAO = new CadastroPaciente();
		pacienteDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return pacienteDAO;
	}
	
	public static CadastroProfissao criarProfissaoDAO() {
		CadastroProfissao profissaoDAO = new CadastroProfissao();
		profissaoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return profissaoDAO;
	}
	
	public static CadastroQueixa criarQueixaDAO() {
		CadastroQueixa 	queixaDAO = new CadastroQueixa();
		queixaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return queixaDAO;
	}
	
	public static CadastroRaca criarRacaDAO() {
		CadastroRaca 	racaDAO = new CadastroRaca();
		racaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return racaDAO;
	}
	
	public static CadastroSimulacao criarSimulacaoDAO() {
		CadastroSimulacao 	simulacaoDAO = new CadastroSimulacao();
		simulacaoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return simulacaoDAO;
	}
	
	public static CadastroSom criarSomDAO() {
		CadastroSom 	somDAO = new CadastroSom();
		somDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return somDAO;
	}
	
	
	public static CadastroTipoExame criarTipoExameDAO() {
		CadastroTipoExame 	tipoexameDAO = new CadastroTipoExame();
		tipoexameDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return tipoexameDAO;
	}
	
	public static CadastroTipoQueixa criarTipoQueixaDAO() {
		CadastroTipoQueixa 	tipoqueixaDAO = new CadastroTipoQueixa();
		tipoqueixaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return tipoqueixaDAO;
	}
	
	public static CadastroTipoUsuario criarTipoUsuarioDAO() {
		CadastroTipoUsuario 	tipousuarioDAO = new CadastroTipoUsuario();
		tipousuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return tipousuarioDAO;
	}
	
	public static CadastroTipoCaracteristica criarTipoCaracteristicaDAO() {
		CadastroTipoCaracteristica 	tipocaracteristicaDAO = new CadastroTipoCaracteristica();
		tipocaracteristicaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return tipocaracteristicaDAO;
	}
	
	public static CadastroCaracteristica criarCaracteristicaDAO() {
		CadastroCaracteristica 	caracteristicaDAO = new CadastroCaracteristica();
		caracteristicaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return caracteristicaDAO;
	}
	
	public static CadastroDiagnostico criarDiagnosticoDAO() {
		CadastroDiagnostico 	diagnosticoDAO = new CadastroDiagnostico();
		diagnosticoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return diagnosticoDAO;
	}
	
	public static CadastroCondutaMedica criarCondutaMedicaDao() {
		CadastroCondutaMedica 	condutaMedicaDAO = new CadastroCondutaMedica();
		condutaMedicaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return condutaMedicaDAO;
	}
	
	public static CadastroJogo criarJogoDao() {
		CadastroJogo 	jogoDAO = new CadastroJogo();
		jogoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return jogoDAO;
	}
	

	public static CadastroAvaliacao criarAvaliacaoDAO() {
		CadastroAvaliacao 	avaliacaoDAO = new CadastroAvaliacao();
		avaliacaoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return avaliacaoDAO;
	}
	
	public static CadastroTurma criarTurmaDAO() {
		CadastroTurma 	turmaDAO = new CadastroTurma();
		turmaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return turmaDAO;
	}
	
	public static CadastroCID criarCidDAO() {
		CadastroCID 	cidDao = new CadastroCID();
		cidDao.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return cidDao;
	}
	
}
