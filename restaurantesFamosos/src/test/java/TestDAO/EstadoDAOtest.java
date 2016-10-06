package TestDAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dao.EstadoDAO;
import factory.DAOFactory;
import model.Estado;
import model.Pais;

public class EstadoDAOtest {

	@Test
	public void deveInserirEstadoNova() {
		Estado estado = new Estado();
		estado.setNome("SC");
		estado.setPais(new Pais(1L));
		EstadoDAO EstadoDAO = DAOFactory.get().estadoDAO();
		EstadoDAO.inserir(estado);
		assertNotNull(estado.getCodigo());
		EstadoDAO.excluir(estado.getCodigo());
	}

	@Test
	public void deveAlterarEstado() {
		Estado Estado = new Estado();
		Estado.setNome("PR");
		Estado.setPais(new Pais(1L));
		EstadoDAO EstadoDAO = DAOFactory.get().estadoDAO();
		EstadoDAO.inserir(Estado);
		Estado.setNome("PR alterado");
		EstadoDAO.alterar(Estado);
		Estado EstadoBanco = EstadoDAO.get(Estado.getCodigo());
		assertEquals("PR alterado", EstadoBanco.getNome());
		EstadoDAO.excluir(EstadoBanco.getCodigo());
	}

}
