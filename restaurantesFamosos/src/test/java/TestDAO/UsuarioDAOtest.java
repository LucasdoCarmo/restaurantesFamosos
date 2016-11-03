package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import dao.UsuarioDAO;
import factory.DAOFactory;
import model.Usuario;

public class UsuarioDAOtest {

	@Test
	public void deveInserirNovoUsuario() {
		Usuario user = new Usuario();
		user.setNome("T");
		user.setSenha("test");
		UsuarioDAO UsuarioDAO = DAOFactory.get().usuarioDAO();
		UsuarioDAO.inserir(user);
		assertNotNull(user.getCodigo());
		UsuarioDAO.excluir(user.getCodigo());
	}

	@Test
	public void deveAlterarUsuario() {
		Usuario user = new Usuario();
		user.setNome("L");
		user.setSenha("t");
		UsuarioDAO usuarioDAO = DAOFactory.get().usuarioDAO();
		usuarioDAO.inserir(user);
		user.setNome("Usuario alterado");
		user.setSenha("asdas");
		usuarioDAO.alterar(user);
		Usuario EstadoBanco = usuarioDAO.get(user.getCodigo());
		assertEquals("Usuario alterado", EstadoBanco.getNome());
		assertEquals("asdas", EstadoBanco.getSenha());
		usuarioDAO.excluir(EstadoBanco.getCodigo());
		
		
		
	}
}
