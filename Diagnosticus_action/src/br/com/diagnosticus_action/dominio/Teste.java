package br.com.diagnosticus_action.dominio;

import java.nio.channels.SeekableByteChannel;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import br.com.diagnosticus_action.Cadastro.CadastroSimulacao;
import br.com.diagnosticus_action.util.DAOFactory;
import br.com.diagnosticus_action.util.HibernateUtil;

public class Teste {
	public static void main(String[] args) throws SQLException {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
		
//		Raca raca = new Raca();
//		Raca raca2 = new Raca();
//		Raca raca3 = new Raca();
//		Raca raca4 = new Raca();
//		Raca raca5 = new Raca();
//		
//		EstadoCivil ec = new EstadoCivil();
//		EstadoCivil ec2 = new EstadoCivil();
//		EstadoCivil ec3 = new EstadoCivil();
//		EstadoCivil ec4 = new EstadoCivil();
//		EstadoCivil ec5 = new EstadoCivil();
//		
//		Genero g = new Genero();
//		Genero g2 = new Genero();
//		Genero g3 = new Genero();
//		Profissao p = new Profissao();
//		Naturalidade n = new Naturalidade();
//		Paciente paciente = new Paciente();
//		
//		TipoQueixa tq = new TipoQueixa();
//		TipoQueixa tq2 = new TipoQueixa();
//		TipoExame te = new TipoExame();
//		Queixa q = new Queixa();
//		CasoEmergencial ce = new CasoEmergencial();
//		Exame e = new Exame();
//		ImagemExames ie = new ImagemExames();
//		
//		TipoUsuario tu = new TipoUsuario();
//		TipoUsuario tu2 = new TipoUsuario();
//		raca.setRaca("branca");
//		ec.setEstadoCivil("teste");
//		g.setGenero("genero");
//		tq.setDescricaoTipoQueixa("dor");
//		session.save(raca);
//		session.save(ec);
//		session.save(tq);
//		session.save(g);
//		
//		tu.setTipoUsuario("Professor");
//		tu2.setTipoUsuario("Aluno");
//		
//		session.save(tu);
//		session.save(tu2);
//		
//		
//		TipoCaracteristica tipo = new TipoCaracteristica();
//		tipo.setDescricaoTipoCaracteristica("Sinal");
//		TipoCaracteristica tipo2 = new TipoCaracteristica();
//		tipo2.setDescricaoTipoCaracteristica("Sintoma");
//		session.save(tipo);
//		session.save(tipo2);
	
//		TipoExame t = new TipoExame();
//		t.setDescricaoTipoExame("Laboratorial");
//		session.save(t);
		
//		StatusSimulacao s = new StatusSimulacao();
//		StatusSimulacao ss = new StatusSimulacao();
//		
//		s.setStatusSimulacao("Nova");
//		ss.setStatusSimulacao("Concluída");
//		
//		session.save(s);
//		session.save(ss);
//		EstadoCivil es = new EstadoCivil();
//		EstadoCivil es2 = new EstadoCivil();
//		es.setEstadoCivil("Divorciado");
//		es2.setEstadoCivil("Viúvo");
//		
//		
//		session.save(es);
//		session.save(es2);
		// Transacao
//		session.getTransaction().commit();
////		session.close();
//		
//		CadastroSimulacao c = new CadastroSimulacao();
//		c = DAOFactory.criarSimulacaoDAO();
//		c.consultarAlunos(18);
	}
}
