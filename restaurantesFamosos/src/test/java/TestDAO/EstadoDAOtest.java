package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import dao.EstadoDAO;
import factory.DAOFactory;
import model.Estado;

public class EstadoDAOtest {

	@Test
	public void deveInserirEstadoNova() {
		Estado estado = new Estado();
		estado.setNome("Amazonas");
		estado.setUf("SC");
		EstadoDAO EstadoDAO = DAOFactory.get().estadoDAO();
		EstadoDAO.inserir(estado);
		assertNotNull(estado.getCodigo());
		EstadoDAO.excluir(estado.getCodigo());
	}

	@Test
	public void deveAlterarEstado() {
		Estado Estado = new Estado();
		Estado.setNome("PR ");
		Estado.setUf("MT");
		EstadoDAO EstadoDAO = DAOFactory.get().estadoDAO();
		EstadoDAO.inserir(Estado);
		Estado.setNome("PR alterado");
		Estado.setUf("MT alterado");
		EstadoDAO.alterar(Estado);
		Estado EstadoBanco = EstadoDAO.get(Estado.getCodigo());
		assertEquals("PR alterado", EstadoBanco.getNome());
		EstadoDAO.excluir(EstadoBanco.getCodigo());
	}

}
