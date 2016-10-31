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
		user.setNome("Lucas do rio verde");
		user.setEmail("test@test.com");
		user.setSenha("test");
		UsuarioDAO UsuarioDAO = DAOFactory.get().usuarioDAO();
		UsuarioDAO.inserir(user);
		assertNotNull(user.getCodigo());
		UsuarioDAO.excluir(user.getCodigo());
	}

	@Test
	public void deveAlterarUsuario() {
		Usuario user = new Usuario();
		user.setNome("Lucas do rio verde");
		user.setEmail("test@test.com");
		user.setSenha("test");
		UsuarioDAO usuarioDAO = DAOFactory.get().usuarioDAO();
		usuarioDAO.alterar(user);
		user.setNome("Usuario alterado");
		user.setEmail("asdsa");
		user.setSenha("asdas");
		
		Usuario EstadoBanco = usuarioDAO.get(user.getCodigo());
		assertEquals("Usuario alterado", EstadoBanco.getNome());
		assertEquals("asdsa", EstadoBanco.getEmail());
		assertEquals("asdas", EstadoBanco.getSenha());
		usuarioDAO.excluir(user.getCodigo());
	}
}
