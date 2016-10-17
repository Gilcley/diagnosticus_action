package br.com.diagnosticus_action.Cadastro;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.diagnosticus_action.dominio.Usuario;

public class CadastroUsuario {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public void salvar(Usuario usuario) {
		this.session.save(usuario);

	}

	public void atualizar(Usuario usuario) {
		this.session.update(usuario);
	}

	public void excluir(Usuario usuario) {
		this.session.delete(usuario);
	}

	public Usuario carregar(Integer codigo) {
		return (Usuario) this.session.get(Usuario.class, codigo);
	}

	public List<Usuario> listar() {
		return this.session.createCriteria(Usuario.class).list();
	}
	
	public Usuario buscarPorMatricula(String matricula){
		String hql = "select u from Usuario u where u.Matricula = :matricula";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("matricula", matricula);
		return (Usuario) consulta.uniqueResult();
	}
	
	public Usuario buscarPorEmail(String email){
		String hql = "select u from Usuario u where u.Email = :email";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("email", email);
		return (Usuario) consulta.uniqueResult();
	}
	
	public Usuario buscar(String matricula, String senha){
		String hql = "select u from Usuario u where u.Matricula = :matricula and u.Senha = :senha";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("senha", senha);
		consulta.setString("matricula", matricula);
		return (Usuario) consulta.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> findUsuarioByNome(String nome){
		String sql= " select u.idusuario, u.nome from usuario u where u.idtipousuario = 2 and u.nome ilike ('"+nome+"%')";
		Query consulta = session.createSQLQuery(sql);
		List<Object> lista = consulta.list();
		List<Usuario> resultado= new ArrayList<>();
		Usuario usuario = new Usuario();
		for (int i = 0; i < lista.size(); i++) {
			Object [] coluna= (Object[]) lista.get(i);
			usuario.setIdUsuario((Integer) coluna [0]);
			usuario.setNome((String) coluna[1]);
			resultado.add(usuario);
			usuario = new Usuario();
		}
		return resultado;
	}

}
